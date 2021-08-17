# Jarvis

> @author: LinJK
>
> @email: linjk121@163.com

# How To Start Jarvis

> Folder: Jarvis/ControlCenter

- Step1: Start Nacos

    `./startup.sh -m standalone`
    
- Step2: Start Sentinel

    `java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar`
    
- Step3: Start Redis
    
    `/Users/linjk/Documents/usr/services/redis-6.2.5/src/redis-server /Users/linjk/Documents/code/java/Jarvis/ControlCenter/conf/redis/redis.conf`
    
    > Test Connection
    `/Users/linjk/Documents/usr/services/redis-6.2.5/src/redis-cli -h 127.0.0.1 -p 6379`
    
                                                                                                                                                                                    
                                                                                                                                                                                    