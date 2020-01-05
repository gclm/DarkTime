
springboot项目提示“Failed to determine a suitable driver class”
=============================================================


之前写的一个springboot + mybatis的项目，之前运行的好好的，今天在家里电脑上突然跑不起来了，一直提示“Failed to determine a suitable driver class”，完整错误信息如下：

    2019-05-04 16:59:55.084  INFO 5504 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
    2019-05-04 16:59:55.084  INFO 5504 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 3530 ms
    2019-05-04 16:59:55.310  WARN 5504 --- [           main] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'applyController': Unsatisfied dependency expressed through field 'applyService'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'applyServiceImpl': Unsatisfied dependency expressed through field 'baseMapper'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'applyMapper' defined in file [E:\workspace2\reader_v2\target\classes\com\wolffy\reader\mapper\ApplyMapper.class]: Unsatisfied dependency expressed through bean property 'sqlSessionFactory'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'sqlSessionFactory' defined in class path resource [org/mybatis/spring/boot/autoconfigure/MybatisAutoConfiguration.class]: Unsatisfied dependency expressed through method 'sqlSessionFactory' parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource' defined in class path resource [org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.zaxxer.hikari.HikariDataSource]: Factory method 'dataSource' threw exception; nested exception is org.springframework.boot.autoconfigure.jdbc.DataSourceProperties$DataSourceBeanCreationException: Failed to determine a suitable driver class
    2019-05-04 16:59:55.318  INFO 5504 --- [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
    2019-05-04 16:59:55.364  INFO 5504 --- [           main] ConditionEvaluationReportLoggingListener : 
    
    Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
    2019-05-04 16:59:55.377 ERROR 5504 --- [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 
    
    ***************************
    APPLICATION FAILED TO START
    ***************************
    
    Description:
    
    Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
    
    Reason: Failed to determine a suitable driver class
    
    
    Action:
    
    Consider the following:
    	If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
    	If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).

错误提示中，看到是没有找到数据库的配置，但是我可以确定在application.properties文件中是有写的。

查阅资料，网上大体有三种解决方案：  

1、使用exclude= {DataSourceAutoConfiguration.class}排除DataSource的自动配置；

2、检查是否将resources文件夹设置成了Resources Root目录（名字是否有写错）；

3、在pom.xml的build标签中添加resources指定资源目录；

但是经过测试，这三种解决方案都没有解决我的问题，第一种直接排除，我是需要springboot对于DataSource的自动配置的，第二种我也确认没问题，名字没写错，而且文件夹也设置成了Resources Root，第三种也不适合，此方式一般是用来加载resources目录以外的资源文件。

![springboot项目提示“Failed to determine a suitable driver class”](http://cdn.jiweichengzhu.com/upload/image/20190505/w/34da4c50-be19-4ff0-b52e-e12c4b0ddb27.png)

一直到今天才发现，原来是**application.properties文件没有编译到classpath中去**。

原因是找到了，但是让我感到不解的地方有三点：

1、使用mvn package打包时，是会将application.properties文件打进去，就是idea在build project的时候不行；

2、不是必现，时好时坏，同一个idea，其他项目没问题；

3、在公司电脑上没问题，就在家里的电脑上有问题；

![springboot项目提示“Failed to determine a suitable driver class”](http://cdn.jiweichengzhu.com/upload/image/20190505/w/24cd491a-6d56-4ad0-9451-ed59d72547de.png)

上图中是我使用mvn package手动打包编译的文件，一切正常，没有丢失文件，但是在自动编译的时候就会**时不时丢失红色框中的文件**，而且文件夹中的文件也不完整，可是java文件却没有丢失过，非常的匪夷所思。

尝试了很多种方式：

1、mvn clean或手动删除target目录，重新编译；

2、删掉项目，重新导入；

3、重启idea，重启电脑；

通通不行，由于上图中的ehcache.xml文件是我最近新建的，所以我一度怀疑是不是以前的文件在idea的编译文件中丢失了管理，我又跑去创建了一个springboot的新项目，比对了一下.idea文件夹中的compiler.xml，但是并没有发现什么不一样，而且我再次新建了一个文件，测试了几次以后发现也会丢失，自动编译的时候也没有拷贝到classpath下面去。

就在我准备放弃这个旧的项目，想要重新建一个项目将代码全部copy过去的时候，在csdn上发现了一篇大佬写的帖子（http://blog.csdn.net/wungmc/article/details/53793177），上面也提到了**IDEA不自动复制资源文件到编译目录classes**的问题，几乎跟我的情况一模一样，也是公司的机器上没问题，到了家里的电脑上就会有问题，大佬的解决方案是：**rebuild project**

抱着万分期待的心情，我也去点了一下rebuild，点完之后跑去看了一下classpath目录，发现文件并没有增加，application.properties还是没有被拷贝过来，灰心丧气，可就在这一瞬间，又想到是不是需要删掉重新生成呢？就在这时候，奇迹出现了，我删掉target之后，重新build project，这次竟然没有丢失文件了。

有点儿不敢置信，害怕又是时好时坏所致，为了确定问题真的解决了，我连续编译了10次，每次都能成功，问题真的解决了~

标签： springboot mybatis