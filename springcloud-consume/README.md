# springcloud-consume
springcloud-consume


##Spring Cloud Consume

消费服务提供者(eureka-client)的接口

1.maven依赖

    <dependency>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-starter-actuator</artifactId>
    		</dependency>
    
    		<dependency>
    			<groupId>org.springframework.cloud</groupId>
    			<artifactId>spring-cloud-starter-eureka</artifactId>
    		</dependency>

2.添加配置文件

    spring.application.name=eureka-consumer
    server.port=20001
    eureka.client.serviceUrl.defaultZone=http://localhost:10000/eureka/

3.更改应用类
  
    
	@SpringBootApplication
    @EnableDiscoveryClient
    public class ConsumeApplication {
    
    	@Bean
    	public RestTemplate restTemplate() {
    		return new RestTemplate();
    	}
    
    	public static void main(String[] args) {
    		new SpringApplicationBuilder(ConsumeApplication.class).web(true).run(args);
    	}
    }
    
    
创建应用主类。初始化RestTemplate，用来真正发起REST请求    
@EnableDiscoveryClient注解用来将当前应用加入到服务治理体系中。

	
	