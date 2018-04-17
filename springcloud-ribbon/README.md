# springcloud-ribbon
SpringCloud ribbon

服务消费者
当前版本为1.5.4RELEASE
创建ribbon
1.maven依赖
    
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
                <artifactId>spring-cloud-starter-ribbon</artifactId>
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


2.添加配置文件，与consumer相同，更改下端口和名字

    spring.application.name=eureka-ribbon
    server.port=20003
    eureka.client.serviceUrl.defaultZone=http://localhost:10000/eureka/

3.更改应用类，添加controller

    @EnableDiscoveryClient
    @SpringBootApplication
    public class RibbonApplication {
    
    	@Bean
    	@LoadBalanced
    	public RestTemplate restTemplate() {
    
    		return new RestTemplate();
    	}
    
    	public static void main(String[] args) {
    		new SpringApplicationBuilder(RibbonApplication.class).web(true).run(args);
    	}
    }

Controller

    @RestController
    public class TestController {
    
        @Autowired
        RestTemplate restTemplate;
    
        @GetMapping("/consumer")
        public String consumer() {
            return restTemplate.getForObject("http://eureka-client/hello", String.class);
    
        }
    }    
    
与consumer不同的是ribbon不用 url 和 port，而是根据name+GetMapping()所找到API接口消费   