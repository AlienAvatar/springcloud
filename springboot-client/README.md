# SpringCloud-client
springcloud-client

##创建“服务提供方”
###springclient

1.maven依赖
    
这是springboot 1.5.4版本，后续更新2.0版本
    
    <parent>
    		<groupId>org.springframework.boot</groupId>
    		<artifactId>spring-boot-starter-parent</artifactId>
    		<version>1.5.4.RELEASE</version>
    		<relativePath/> <!-- lookup parent from repository -->
    	</parent>
    <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
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
   

 2.更改应用类Application       
 
    @EnableDiscoveryClient
    @SpringBootApplication
    public class ClientApplication {
    
    	public static void main(String[] args) {
    		new SpringApplicationBuilder(ClientApplication.class).web(true).run();
    	}
    }
    
3.添加配置文件
   
    spring.application.name=eureka-client
    server.port=2001
    eureka.client.serviceUrl.defaultZone=http://localhost:1001/eureka/
    
先启动注册服务，再启动服务提供方


Spring Cloud Consul

//还需要改进

Spring Cloud Consul项目是针对Consul的服务治理实现。Consul是一个分布式高可用的系统，它包含多个组件，但是作为一个整体，在微服务架构中为我们的基础设施提供服务发现和服务配置的工具。

1.maven依赖

    <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
    </dependency>‘

2.添加配置
    spring.cloud.consul.host=localhost
    spring.cloud.consul.port=20001

我们将eureka-client转换为基于consul服务治理的服务提供者就完成了。
