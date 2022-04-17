'use strict';

let localVideo  = document.querySelector('video');
let audioSource = document.querySelector("select#audioSource");
let audioOutput = document.querySelector("select#audioOutput");
let videoSource = document.querySelector("select#videoSource");

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

//判断浏览器是否支持这些 API
if (!navigator.mediaDevices || !navigator.mediaDevices.enumerateDevices) {
    console.log("enumerateDevices() not supported.");
}
else {
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

navigator.mediaDevices.getUserMedia(mediaStreamContrains).then(
    gotLocalMediaStream
).catch(
    handleLocalMediaStreamError
);

function gotLocalMediaStream(mediaStream){
    localVideo.srcObject = mediaStream;
}

function handleLocalMediaStreamError(error){
    console.log('navigator.getUserMedia error: ', error);
}

function handleError(err){
    console.log(err.name + " : " + err.message);
}