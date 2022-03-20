# 动态多数据源

- 1、利用aop动态切换数据源；
- 2、jpa和mybatis plus混合使用，同一个接口里面可以同时使用jpa和mybatis plus；
- 3、实现了事物控制；
- 4、读取数据源的方式通过map读取配置；
- 5、模拟多租户切换数据源。

##### 数据库基于spring jdbc DriverManagerDataSource 
```
没有使用数据库连接池，需要的自行在DsConfig里面的创建对象里面修改
```