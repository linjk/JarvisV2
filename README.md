# Jarvis

> @author: LinJK
>
> @email: linjk121@163.com

# Version

> spring boot     2.3.2.RELEASE
> disruptor       3.3.2

# How To Start Jarvis

> Folder: Jarvis/ControlCenter

- Step1: Start Nacos

    `./startup.sh -m standalone`
    
- Step2: Start Sentinel

    `java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.0.jar`
    
- Step3: Start Redis
    
    `/Users/linjk/Documents/usr/services/redis-6.2.5/src/redis-server /Users/linjk/Documents/code/java/Jarvis/ControlCenter/conf/redis/redis.conf`
    
    > Test Connection
    `/Users/linjk/Documents/usr/services/redis-6.2.5/src/redis-cli -h 127.0.0.1 -p 6379 -a {你的密码}`
    
                                                                                                                                                                                    
                                                                                                                                                                                    