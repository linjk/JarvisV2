'use strict';

let localVideo  = document.querySelector('video');
let audioSource = document.querySelector("select#audioSource");
let audioOutput = document.querySelector("select#audioOutput");
let videoSource = document.querySelector("select#videoSource");
//picture
let snapshot = document.querySelector('button#snapshot');
let picture = document.querySelector('canvas#picture');
let savePicture = document.querySelector('button#save');
picture.width = 640;
picture.height = 480;

const mediaStreamContrains = {
    video: {
        frameRate: {min: 20},
        width: {min: 640, ideal: 640},
        height: {min: 360, ideal: 360},
        aspectRatio: 16/9
    },
    audio: {
        echoCancellation: true,
        noiseSuppression: true,
        autoGainControl: true
    }
};

function start() {
    if (!navigator.mediaDevices || !navigator.mediaDevices.enumerateDevices) {
        console.log("enumerateDevices() not supported.");
    }
    else {
        navigator.mediaDevices.getUserMedia(mediaStreamContrains).then(function(mediaStream) {
            window.stream = mediaStream;
            localVideo.srcObject = mediaStream;
        }).catch(handleError);

        navigator.mediaDevices.enumerateDevices()
            .then(function(deviceInfos) {
                deviceInfos.forEach(function(deviceInfo) {
                    console.log(deviceInfo.kind + ": label = " + deviceInfo.label + ": id = " + deviceInfo.deviceId + ": groupId = " + deviceInfo.groupId);

                    let option = document.createElement('option');
                    option.text = deviceInfo.label;
                    option.value = deviceInfo.deviceId;
                    if(deviceInfo.kind === 'audioinput'){
                        audioSource.appendChild(option);
                    }
                    else if(deviceInfo.kind === 'audiooutput'){
                        audioOutput.appendChild(option);
                    }
                    else if(deviceInfo.kind === 'videoinput'){
                        videoSource.appendChild(option);
                    }
                });
            })
            .catch(handleError);
    }
}

function handleError(err){
    console.log(err.name + " : " + err.message);
}

snapshot.onclick = function() {
    picture.getContext('2d').drawImage(localVideo, 0, 0, picture.width, picture.height);
}

function downLoad(url){
    let oA = document.createElement("a");
    oA.download = 'photo';// 设置下载的文件名，默认是'下载'
    oA.href = url;
    document.body.appendChild(oA);
    oA.click();
    oA.remove(); // 下载之后把创建的元素删除
}

savePicture.onclick = function (){
    downLoad(picture.toDataURL("image/jpeg"));
}

// -----------------------------------------------------------------------------

start();