

说明：以下步骤是从官网翻译的，部分略有不同，总体思路一致
http://docs.spring.io/spring-boot/docs/current/reference/html/howto-traditional-deployment.html

#标题：创建一个可部署的WAR包

##1.启动类添加Servlet支持

@SpringBootApplication
public class SpringbootDemoJspApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringbootDemoJspApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoJspApplication.class, args);
	}
}

##2.配置文件添加MVC映射
     
      spring.mvc.view.prefix=/WEB-INF/views/  #默认从Web Resource Directory开始寻找
      spring.mvc.view.suffix=.jsp

##3.项目结构添加Web资源路径

       手工创建Web资源目录src/main/webapp
      
       为什么要推荐这个默认目录名称？理由如下：
       
       虽然在启动时已经通过SpringBootServletInitializer重写config方法来获得Servlet支持。
       也就是其实JSP放在哪里都可以访问到，但为什么还是要默认新建webapp目录来专门存放JSP呢？ 
       因为MAVEN的package命令默认会将webapp目录打进WAR包 其他名称的话需要自己在插件定义位置。

##4.POM添加依赖

        <!--项目修改为WAR类型 -->
        <packaging>war</packaging>
        
		<!-- JSP相关依赖 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope> <!--由部署容器来提供依赖，避免JAR冲突 -->
		</dependency>

		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope> <!--由部署容器来提供依赖，避免JAR冲突 -->
		</dependency>

		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
		</dependency>

##5. IDE部署项目

     IDEA/Eclipse + Tomcat 8.5 +  springboot-war-jsp:war exploded  同源发布
    