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