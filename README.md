# Jarvis

> @author: LinJK
>
> @email: linjk121@163.com

# Version

> JDK                  openjdk version "14.0.2" 2020-07-14
>
> Spring cloud alibaba 2.2.5.RELEASE
>
> Nacos                1.4.1
>
> Spring boot          2.3.2.RELEASE
>
> Disruptor            3.3.2

# How To Start Jarvis

> Folder: Jarvis/ControlCenter

- Step1: Start Nacos

    `./startup.sh -m standalone`
    
- Step2: Start Sentinel

    `java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.0.jar`
    
    默认的用户名和密码均为: sentinel
    
- Step3: Start Redis
    
    如下为单节点，已弃用，后续使用容器的三主三从的Redis集群模式：
    `/Users/linjk/Documents/usr/services/redis-6.2.5/src/redis-server /Users/linjk/Documents/code/java/Jarvis/ControlCenter/conf/redis/redis.conf`
    
    > Test Connection
    `/Users/linjk/Documents/usr/services/redis-6.2.5/src/redis-cli -h 127.0.0.1 -p 6379 -a {你的密码}`
    
                                                                                                                                                                                    
                                                                                                                                                                                    