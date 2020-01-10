<meta name="referrer" content="no-referrer" />

**1、安装MariaDB**

安装命令  

yum -y install mariadb mariadb-server

安装完成MariaDB，首先启动MariaDB

systemctl start mariadb

设置开机启动  

systemctl enable mariadb

接下来进行MariaDB的相关简单配置  

mysql\_secure\_installation

首先是设置密码，会提示先输入密码

Enter current password for root (enter for none):<–初次运行直接回车

设置密码

Set root password? \[Y/n\] <– 是否设置root用户密码，输入y并回车或直接回车

New password: <– 设置root用户的密码

Re-enter new password: <– 再输入一次你设置的密码  
其他配置  
Remove anonymous users? \[Y/n\] <– 是否删除匿名用户，回车  
Disallow root login remotely? \[Y/n\] <–是否禁止root远程登录,回车,  
Remove test database and access to it? \[Y/n\] <– 是否删除test数据库，回车  

Reload privilege tables now? \[Y/n\] <– 是否重新加载权限表，回车

初始化MariaDB完成，接下来测试登录  

mysql -u root -p 

password

完成。

**2、配置MariaDB的字符集**  
vi /etc/my.cnf  

在\[mysqld\]标签下添加

    init_connect='SET collation_connection = utf8mb4_unicode_ci' 
    init_connect='SET NAMES utf8mb4' 
    character-set-server=utf8mb4 
    collation-server=utf8mb4_unicode_ci 
    skip-character-set-client-handshake

文件/etc/my.cnf.d/client.cnf  
vi /etc/my.cnf.d/client.cnf  

在\[client\]中添加

    default-character-set=utf8mb4

vi /etc/my.cnf.d/mysql-clients.cnf  

在\[mysql\]中添加

    default-character-set=utf8mb4

全部配置完成，重启mariadb

systemctl restart mariadb

之后进入MariaDB查看字符集  
mysql> show variables like "%character%";show variables like "%collation%";  
显示为  

    +--------------------------+----------------------------+
    | Variable_name            | Value                      |
    +--------------------------+----------------------------+
    | character_set_client    | utf8mb4                      |
    | character_set_connection | utf8mb4                      |
    | character_set_database  | utf8mb4                      |
    | character_set_filesystem | binary                    |
    | character_set_results    | utf8mb4                      |
    | character_set_server    | utf8mb4                      |
    | character_set_system    | utf8mb4                      |
    | character_sets_dir      | /usr/share/mysql/charsets/ |
    +--------------------------+----------------------------+
    8 rows in set (0.00 sec)
    
    
    +----------------------+-----------------+
    | Variable_name        | Value          |
    +----------------------+-----------------+
    | collation_connection | utf8mb4_unicode_ci |
    | collation_database  | utf8mb4_unicode_ci |
    | collation_server    | utf8mb4_unicode_ci |
    +----------------------+-----------------+
    3 rows in set (0.00 sec)
    

字符集配置完成。

**3、添加用户，设置权限**

  

![](https://img-blog.csdn.net/2018040319304267?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2p4cTA4MTY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)  

创建用户命令  

mysql>create user username@localhost identified by 'password'; 

![](https://img-blog.csdn.net/20180403193207259?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2p4cTA4MTY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)  

查看用户集

![](https://img-blog.csdn.net/20180403193123348?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2p4cTA4MTY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)  

直接创建用户并授权的命令

 mysql>grant all on \*.\* to username@localhost indentified by 'password'; 

![](https://img-blog.csdn.net/20180403011150334?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2p4cTA4MTY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)  

授予外网登陆权限 mysql>grant all privileges on \*.\* to username@'%' identified by 'password'; 

![](https://img-blog.csdn.net/20180403010611104?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2p4cTA4MTY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)  

![](https://img-blog.csdn.net/20180403011002110?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2p4cTA4MTY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)  

授予权限并且可以授权 mysql>grant all privileges on \*.\* to username@'hostname' identified by 'password' with grant option; 

简单的用户和权限配置基本就这样了。 

其中只授予部分权限把 其中 all privileges或者all改为select,insert,update,delete,create,drop,index,alter,grant,references,reload,shutdown,process,file其中一部分。

4、ECS开放3306端口 这样外网才可以访问

![](https://img-blog.csdn.net/20180403011846928?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2p4cTA4MTY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)  

![](https://img-blog.csdn.net/20180403011959244?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2p4cTA4MTY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)  

5、Navicat 测试连接成功

![](https://img-blog.csdn.net/20180403012144521?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2p4cTA4MTY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)