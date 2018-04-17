# springcloud-zuul
springcloud-zuul

## Spring Cloud Zuul
使用的版本是springboot 1.5.4.RELEASE

服务器网关

服务网关是微服务架构中一个不可或缺的部分。通过服务网关统一向外系统提供REST API的过程中，除了具备服务路由、均衡负载功能之外，它还具备了权限控制等功能。
Spring Cloud Netflix中的Zuul就担任了这样的一个角色，为微服务架构提供了前门保护的作用，同时将权限控制这些较重的非业务逻辑内容迁移到服务路由层面，使得服务集群主体能够具备更高的可复用性和可测试性。


需要启动 springcloud-server 和 springcloud-client

###创建服务器网关

1.maven依赖

    <dependency>
    			<groupId>org.springframework.cloud</groupId>
    			<artifactId>spring-cloud-starter-eureka</artifactId>
    		</dependency>
    
    		<dependency>
    			<groupId>org.springframework.cloud</groupId>
    			<artifactId>spring-cloud-starter-zuul</artifactId>
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
            	
2.创建配置文件

    spring.application.name=eureka-zuul
    server.port=20006
    eureka.client.serviceUrl.defaultZone=http://localhost:10000/eureka/

3.更改应用类
  添加注释@EnableZuulProxy 开启服务器网关
  
    @EnableZuulProxy
    @SpringBootApplication
    public class ZuulApplication {
    
    	public static void main(String[] args) {
    		SpringApplication.run(ZuulApplication.class, args);
    	}
    }   
    
当我们这里构建的 eureka-zuul应用启动并注册到eureka之后，服务网关会发现上面我们启动的两个服务 eureka-client和 eureka-consumer，这时候Zuul就会创建两个路由规则。每个路由规则都包含两部分，一部分是外部请求的匹配规则，另一部分是路由的服务ID。针对当前示例的情况，Zuul会创建下面的两个路由规则：  

eureka-client服务的请求规则为： /eureka-client/**
eureka-consumer服务的请求规则为： /eureka-consumer/**

example:http://localhost:20006/eureka-client/hello ，该请求将最终被路由到eureka-client的/hello接口上。                     	

