## 安装Gitea 

### 流程

- 安装Docker
- 安装 docker-compose
- 创建 web 网络
- 编写 docker-compose.yml
- 构建并启动镜像

### 安装Docker

```shell
# 公网环境
curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun
# 检查指令
$ docker info
```

### 安装 docker-compose

```shell
# 安装 axel
 wget https://dev.tencent.com/u/gclm/p/shell/git/raw/master/linux/axel.sh && chmod +x axel.sh && bash axel.sh
# 安装 docker-compose
axel -a -n 10 -o /usr/local/bin/docker-compose  https://github.com/docker/compose/releases/download/1.25.1/docker-compose-`uname -s`-`uname -m`
# 检查指令
$ docker-compose version
```

### 创建 web 网络

```创建网络
docker network create web
```

### 编写 docker-compose.yml

```yml
version: "3.7"

services:
  gitea:
    image: gitea/gitea:latest
    container_name: gitea
    environment:
      TZ: Asia/Shanghai
    volumes:
      - /opt/gitea:/data
    restart: always
    networks:
      - default
      - web
    ports:
      - "18001:3000"
      - "18002:22"

  db:
    image: postgres:9.6
    restart: always
    environment:
      - POSTGRES_USER=gitea
      - POSTGRES_PASSWORD=gitea
      - POSTGRES_DB=gitea
    volumes:
      - /opt/postgres:/var/lib/postgresql/data
    healthcheck:
      test: "exit 0"

networks:
  web:
    external: true
```

### 构建并启动镜像

```shell
# 构建检查 docker-componse.yml 文件
docker-compose -f docker-compose.yml build
# 运行容器。启动服务
docker-compose -f docker-compose.yml up -d 
```

