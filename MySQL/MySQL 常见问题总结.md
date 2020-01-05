# MySQL 常见问题总结



## MySQL 8.x 问题汇集

**1. MySQL8.x客户端连接数据库报错plugin caching_sha2_password could not be loaded**

![](https://petkit-img3.oss-cn-hangzhou.aliyuncs.com/image/20191018104115.png)

**解决方案：**

- 打开cmd：mysql -uroot -p
- 进入mysql依次执行下面语句

```sql
#修改加密规则 
ALTER USER 'root'@'localhost' IDENTIFIED BY 'password' PASSWORD EXPIRE NEVER; 
#更新一下用户的密码
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';  
#刷新权限
FLUSH PRIVILEGES; 
```


**om.mysql.cj.exceptions.InvalidConnectionAttributeException: The server time zone value '�й���׼ʱ��' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.**

解决方案
 增加时区配置
```
jdbc:mysql://localhost:3306/music?characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true
```