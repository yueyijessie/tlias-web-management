# tlias-web-management
springboot+mybatis案例（增删改查+登录认证校验+事务管理+AOP）

> 登录认证校验

- 会话技术
    - Cookie（客户端）和Session（服务端），不能跨域
    - JWT令牌，支持PC和移动端，集群服务器环境可使用，无需存储在服务器
    - pom文件中引入jwt依赖，jwtUtils生成和解析jwt，LoginController验证用户后下发jwt
- servlet规范中的Filter过滤器
    - 定义过滤器 ：[LoginCheckFilter](src/main/java/com/jessie/filter/LoginCheckFilter.java)
    - 配置过滤器 ：LoginCheckFilter类加上`@WebFilter`注解，配置拦截资源的路径。启动类上加`@ServletComponentScan`开启Servlet组件支持
- Spring提供的interceptor拦截器（推荐）
    - 自定义拦截器：[LoginCheckInterceptor](src/main/java/com/jessie/interceptor/LoginCheckInterceptor.java)
    - 注册配置拦截器：[WebConfig](src/main/java/com/jessie/config/WebConfig.java)
- 过滤器和拦截器之间的区别
    - 接口规范不同：过滤器需要实现Filter接口，而拦截器需要实现HandlerInterceptor接口。
    - 拦截范围不同：过滤器Filter会拦截所有的资源，而Interceptor只会拦截Spring环境中的资源

> 全局异常处理器
- [GlobalExceptionHandler](src/main/java/com/jessie/exception/GlobalExceptionHandler.java)

> 事务管理
- 在业务方法上加上 @Transactional 来控制事务 （[DeptServiceImpl](src/main/java/com/jessie/service/impl/DeptServiceImpl.java)）
- 异常回滚的属性：rollbackFor
- 事务传播行为：propagation （[DeptLogServiceImpl](src/main/java/com/jessie/service/impl/DeptLogServiceImpl.java)）

> AOP（面向特定方法编程）
- pom文件中引入aop依赖
- 自定义注解 [Log](src/main/java/com/jessie/anno/Log.java)
- 在service类上添加Log注解
