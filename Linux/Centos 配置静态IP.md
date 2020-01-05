# Centos 7 配置静态IP

## 前言
> 最近用 VM  虚拟机安装了 Centos7，采用了最新安装模式，现在记录一下如何配置静态IP


## 第一步：将网关配置成 dhcp（动态ip模式）

```shell
[root@localhost network-scripts]# ls /etc/sysconfig/network-scripts/ifcfg-*
/etc/sysconfig/network-scripts/ifcfg-ens33    /etc/sysconfig/network-scripts/ifcfg-lo
 
# 备份网卡
[root@localhost network-scripts]# cp /etc/sysconfig/network-scripts/ifcfg-ens33 /etc/sysconfig/network-scripts/ifcfg-ens33.bak
[root@localhost network-scripts]# yum install -y vim 
[root@localhost network-scripts]# vim /etc/sysconfig/network-scripts/ifcfg-ens33
```

修改内容如下：
```text
BOOTPROTO=dhcp
ONBOOT=yes
```

重启网卡
```shell
[root@localhost network-scripts]# service network restart
```

验证动态ip是否配置成功

```shell
[root@localhost network-scripts]# ping baidu.com
PING baidu.com (220.181.38.148) 56(84) bytes of data.
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=1 ttl=128 time=25.4 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=2 ttl=128 time=27.0 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=3 ttl=128 time=26.6 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=4 ttl=128 time=25.1 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=5 ttl=128 time=24.7 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=6 ttl=128 time=25.7 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=7 ttl=128 time=25.2 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=8 ttl=128 time=24.8 ms

```
可以ping通,则说明动态ip配置完成


## 第二步：获取当前网关和ip区段
```shell
[root@localhost network-scripts]# ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host 
       valid_lft forever preferred_lft forever
2: ens33: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 00:0c:29:20:75:ba brd ff:ff:ff:ff:ff:ff
    inet 192.168.22.131/24 brd 192.168.22.255 scope global noprefixroute ens33
       valid_lft forever preferred_lft forever
    inet6 fe80::cb27:d176:7700:6841/64 scope link noprefixroute 
       valid_lft forever preferred_lft forever
[root@localhost network-scripts]# ip route
default via 192.168.22.2 dev ens33 proto static metric 100 
192.168.22.0/24 dev ens33 proto kernel scope link src 192.168.22.131 metric 100 
```

从上述信息可以得出:
ip：192.168.22.131
子网掩码：255.255.255.0
网关：192.168.22.2

### 第三步： 设置静态ip


```shell
[root@localhost network-scripts]# vim /etc/sysconfig/network-scripts/ifcfg-ens33
```

修改内容如下：
```text
TYPE="Ethernet"
PROXY_METHOD="none"
BROWSER_ONLY="no"
#BOOTPROTO="dhcp"
BOOTPROTO="static"   # 修改内容 静态IP
DEFROUTE="yes"
IPV4_FAILURE_FATAL="no"
IPV6INIT="yes"
IPV6_AUTOCONF="yes"
IPV6_DEFROUTE="yes"
IPV6_FAILURE_FATAL="no"
IPV6_ADDR_GEN_MODE="stable-privacy"
NAME="ens33"
UUID="54684693-c6f8-40b1-9195-a8c479015524"
DEVICE="ens33"
ONBOOT="yes"
IPADDR=192.168.22.131  # 新增内容 静态IP
GATEWAY=192.168.22.2   # 新增内容 动态网关
DNS1=8.8.8.8           # 新增内容  dns
```

![](https://petkit-img3.oss-cn-hangzhou.aliyuncs.com/image/20191021155025.png)

重启网卡

```shell
[root@localhost network-scripts]# service network restart
```

验证静态ip 是否配置成功

```shell
[root@localhost network-scripts]# ping baidu.com
PING baidu.com (220.181.38.148) 56(84) bytes of data.
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=1 ttl=128 time=25.4 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=2 ttl=128 time=27.0 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=3 ttl=128 time=26.6 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=4 ttl=128 time=25.1 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=5 ttl=128 time=24.7 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=6 ttl=128 time=25.7 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=7 ttl=128 time=25.2 ms
64 bytes from 220.181.38.148 (220.181.38.148): icmp_seq=8 ttl=128 time=24.8 ms

```
可以ping通,则说明静态 ip 配置完成