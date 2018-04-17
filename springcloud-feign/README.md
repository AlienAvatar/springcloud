# springcloud-feign
springcloud-feign


## Spring Cloud Feign

Spring Cloud Feign是一套基于Netflix Feign实现的声明式服务调用客户端

###创建Spring Cloud Feign
使用的是1.5.4RELEASE版本

    <dependency>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-starter-actuator</artifactId>
    		</dependency>
    
    		<dependency>
    			<groupId>org.springframework.cloud</groupId>
    			<artifactId>spring-cloud-starter-eureka</artifactId>
    		</dependency>
    
    		<dependency>
    			<groupId>org.springframework.cloud</groupId>
    			<artifactId>spring-cloud-starter-feign</artifactId>
    		</dependency>
    		
    	<dependencyManagement>
        		<dependencies>
        			<dependency>
        				<groupId>org.springframework.cloud</groupId>
        				<artifactId>spring-cloud-dependencies</artifactId>
        				<version>Dalston.SR1</version>
        				<type>pom</type>
        				<scope>import</scope>
        			</dependency>
        		</dependencies>
        	</dependencyManagement>


2.添加配置文件
    
    spring.application.name=eureka-feign
    server.port=20004
    eureka.client.serviceUrl.defaultZone=http://localhost:10000/eureka/

3.更改controller，和应用类
    
    @EnableFeignClients
    @EnableDiscoveryClient
    @SpringBootApplication
    public class FeignApplication {
    
    	public static void main(String[] args) {
    		new SpringApplicationBuilder(FeignApplication.class).web(true).run(args);
    	}
    }
    
Controller
   
   添加接口
   
    @FeignClient("eureka-client")
    public interface ITestFeign {
        @GetMapping("/hello") //这是服务提供方的 GetMapping
        String consumer();
    }
    
调用接口
        
    @RestController
    public class TestController {
    
        @Autowired
        ITestFeign iTestFeign;
    
        @GetMapping("/consumer")
        public String consumer(){
            return iTestFeign.consumer();
        }
    }

相比consumer 和 ribbon 
   feign通过@FeignClient定义的接口来统一的生命我们需要依赖的微服务接口
   由于Feign是基于Ribbon实现的，所以它自带了客户端负载均衡功能，也可以通过Ribbon的IRule进行策略扩展。
   Feign还整合的Hystrix来实现服务的容错保护