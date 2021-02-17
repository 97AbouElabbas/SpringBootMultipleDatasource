# Deploy Spring boot application on weblogic 12C (12.2.1.0)

## Maven Dependences
````
<packaging>war</packaging>
.
.
<properties>
	<java.version>1.8</java.version>
	<maven.test.skip>true</maven.test.skip>
</properties>
.
.
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-tomcat</artifactId>
	<scope>provided</scope>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>wlclient</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>D:\AllianzTask\Spring\wlclient.jar</systemPath>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>jrmpclient</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>D:\Spring\jrmpclient.jar</systemPath>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>weblogic</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>D:\Spring\weblogic.jar</systemPath>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>javax.transaction</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>D:\Spring\javax.transaction.jar</systemPath>
</dependency>
````

## Edit generated Application class

Extend SpringBootServletInitializer, to implement the WebApplicationInitializer interface and to override the configure method.

````
@ComponentScan
@SpringBootApplication
public class SpringBootWeblogicApplication extends SpringBootServletInitializer implements WebApplicationInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWeblogicApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWeblogicApplication.class);
	}
}
````

## Create weblogic.xml under src/main/webapp/WEB-INF
````
<?xml version="1.0" encoding="UTF-8"?>
<wls:weblogic-web-app 
	xmlns:wls="http://xmlns.oracle.com/weblogic/weblogic-web-app" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
						http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd 
						http://xmlns.oracle.com/weblogic/weblogic-web-app 
						http://xmlns.oracle.com/weblogic/weblogic-web-app/1.9/weblogic-web-app.xsd">
    <wls:weblogic-version>12.2.1.0</wls:weblogic-version>
    <wls:context-root>/springboot</wls:context-root>

    <wls:container-descriptor>
        <wls:prefer-application-packages>
            <wls:package-name>org.slf4j.*</wls:package-name>
            <wls:package-name>org.springframework.*</wls:package-name>
            <wls:package-name>com.fasterxml.jackson.*</wls:package-name>
        </wls:prefer-application-packages>
    </wls:container-descriptor>
</wls:weblogic-web-app>
````

## Create dispatcherServlet-servlet.xml under src/main/webapp/WEB-INF
````
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    					   http://www.springframework.org/schema/beans/spring-beans.xsd">
</beans>
````

## Run As/Maven install