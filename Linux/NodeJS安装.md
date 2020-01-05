
# Node.js 安装教程
 
##  安装编译组件

```
yum install gcc-c++ make -y
```

## 安装 node 和 yarn

```
curl -sL https://rpm.nodesource.com/setup_12.x | bash -
curl -sL https://dl.yarnpkg.com/rpm/yarn.repo | sudo tee /etc/yum.repos.d/yarn.repo
yum install -y nodejs
yum install -y yarn
```

## 更换 npm 和 yarn 源

```

```


## 版本查看指令

```
[root@10-13-18-4 ~]# node -v
v12.13.1
[root@10-13-18-4 ~]# npm -v
6.12.1
[root@10-13-18-4 ~]# yarn -v
1.21.1
```