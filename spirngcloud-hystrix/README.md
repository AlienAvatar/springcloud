# spirngcloud-hystrix
springcloud-hystrix

## Spring Cloud Hystrix

服务容错保护
服务容错保护是一系列服务容错保护机制，对远程调用出现网络问题或调用故障，导致请求不断增加，等待相应，而形成任务积压，线程无法释放，最终导致系统瘫痪

Spring Cloud Hystrix中实现了线程隔离、断路器等一系列的服务保护功能

需要启动springcloud 和 springcloud-client

###创建Hystrix
1.maven依赖
    
    <dependency>
    			<groupId>org.springframework.cloud</groupId>
    			<artifactId>spring-cloud-starter-eureka</artifactId>
    		</dependency>
    
    		<dependency>
    			<groupId>org.springframework.cloud</groupId>
    			<artifactId>spring-cloud-starter-ribbon</artifactId>
    		</dependency>
    
    		<dependency>
    			<groupId>org.springframework.cloud</groupId>
    			<artifactId>spring-cloud-starter-hystrix</artifactId>
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
    spring.application.name=eureka-ribbon-hystrix
    server.port=20005
    eureka.client.serviceUrl.defaultZone=http://localhost:10000/eureka/
 
3.更改应用类，添加Controller

    @SpringBootApplication
    @EnableCircuitBreaker
    @EnableDiscoveryClient
    @EnableHystrix //注解开启Hystrix的使用
    public class HystrixApplication {
    
    	@Bean
    	@LoadBalanced
    	public RestTemplate restTemplate() {
    		return new RestTemplate();
    	}
    
    	public static void main(String[] args) {
    //		SpringApplication.run(HystrixApplication.class, args);
    		new SpringApplicationBuilder(HystrixApplication.class).web(true).run(args);
    	}
    }
Controller

    @RestController
        public class TestController {
        
            @Autowired
            DiscoveryClient discoveryClient;
        
            @Autowired
            RestTemplate restTemplate;
        
            @GetMapping("/consumer")
            @HystrixCommand(fallbackMethod = "fallback")//延迟这调用这个方法名
            public String consumer() throws Exception{
                Thread.sleep(5000L);//为了触发降级延迟
                String services = "Services: " + discoveryClient.getServices();
                System.out.println(services);
                return restTemplate.getForObject("http://eureka-client/hello", String.class);
            }
        
            public String fallback() {
                return "fallback";
            }
        }
        
看到服务提供方输出了原本要返回的结果，但是由于返回前延迟了5秒，而服务消费方触发了服务请求超时异常，
服务消费者就通过HystrixCommand注解中指定的降级逻辑进行执行，因此该请求的结果返回了fallback。       