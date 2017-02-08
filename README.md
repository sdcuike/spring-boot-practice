# spring-boot-practice


Spring Boot Practice

##启动：
- mvn spring-boot:run
- 在IDE中运行java应用程序启动

## debug模式启动
- mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

[https://docs.spring.io/spring-boot/docs/1.3.0.BUILD-SNAPSHOT/maven-plugin/run-mojo.html#jvmArguments](https://docs.spring.io/spring-boot/docs/1.3.0.BUILD-SNAPSHOT/maven-plugin/run-mojo.html#jvmArguments)
- 在IDE中debug模式运行java应用程序启动

