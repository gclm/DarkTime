```shell
docker run -it --name teamcity-server \
-v /Volumes/Exploitation/Server/TeamCity/datadir:/data/teamcity_server/datadir \
-v /Volumes/Exploitation/Server/TeamCity/logs:/opt/teamcity/logs \
-p 8111:8111 \
jetbrains/teamcity-server
```