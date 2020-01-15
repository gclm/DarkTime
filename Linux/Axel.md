## 安装脚本

```shell
#在 Debian/Ubuntu/LinuxMint 上安装 Axel
$ sudo apt-get install axel

#在 RHEL/CentOS 上安装 Axel
$ sudo yum install axel

#在 Fedora 上安装 Axel
$ sudo dnf install axel

#在 openSUSE 上安装 Axel
$ sudo zypper install axel

#在 Mageia 上安装 Axel
$ sudo urpmi axel

#在基于 Arch Linux 的发行版安装 Axel
$ sudo pacman -S axel
```

## 语法

```shell
axel [options] url1 [url2] [url...]
```

## 用法

```shell
--max-speed=x , -s x         最高速度x
--num-connections=x , -n x   连接数x
--output=f , -o f            下载为本地文件f
--search[=x] , -S [x]        搜索镜像
--header=x , -H x            添加头文件字符串x（指定 HTTP header）
--user-agent=x , -U x        设置用户代理（指定 HTTP user agent）
--no-proxy ， -N             不使用代理服务器
--quiet ， -q                静默模式
--verbose ，-v               更多状态信息
--alternate ， -a            打印进度信息
--help ，-h                  帮助
--version ，-V               版本信息
```

## 示例

```shell
axel -a -n 10 -o /usr/local/bin/docker-compose  https://github.com/docker/compose/releases/download/1.25.1/docker-compose-`uname -s`-`uname -m`
```



