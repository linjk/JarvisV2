#!/usr/bin/python3

import numpy as np
import scipy.io.wavfile as wf
import matplotlib.pyplot as plt


class WavProc(object):
    @staticmethod
    def wav_read(wav_path):
        assert wav_path.endswith('.wav'), 'Not Supported File Format'

        sr, wav_data = wf.read(wav_path)
        frame_num = wav_data.shape[0]
        # 时长 = 采样点个数 / 采样率
        duration = frame_num/sr
        if len(wav_data.shape) == 1:
            channel_num = 1
        else:
            channel_num = 2

        return sr, duration, channel_num, wav_data

    @staticmethod
    def wav_write(wav_data, output_path, sr):
        assert output_path.endswith('.wav'), 'Not Supported File Format'
        
        wf.write(output_path, sr, wav_data)

    @staticmethod
    def pcm_read(pcm_path, sr):
        assert pcm_path.endswith('.pcm'), 'Not Supported File Format'

        wav_data = np.fromfile(pcm_path, dtype=np.short)

        frame_num = wav_data.shape[0]
        # 时长 = 采样点个数 / 采样率
        duration = frame_num / sr
        if len(wav_data.shape) == 1:
            channel_num = 1
        else:
            channel_num = 2

        return sr, duration, channel_num, wav_data

    def time_domain_display(self, wav_path):
        sr, duration, channel_num, data = self.wav_read(wav_path)
        plt.xlabel('Time')
        plt.ylabel('Amplitude')
        plt.title('Time Domain')

        if channel_num == 1:
            plt.plot(data)
        elif channel_num == 2:
            plt.subplot(211)
            plt.plot(data[:, 0])
            plt.subplot(212)
            plt.plot(data[:, 1])
        
        plt.show()


if __name__ == '__main__':
    wav_path = '/Users/linjk/Documents/code/java/Jarvis/python/voice/my_voice_hello.wav'
    wp = WavProc()
    sr, duration, channel_num, wav_data = wp.wav_read(wav_path)
    print('采样率: %d Hz' % sr)
    print('长度: %d ms' % round(1000*duration))
    print('通道数: %d ch' % channel_num)
    
    #wp.time_domain_display(wav_path)

    # 将音量的幅值降低一半
    #amp_half_data = wav_data * 0.5
    #wp.wav_write(amp_half_data.astype(np.short), './test.wav', sr)

    # wav_path = 'my_voice_hello.pcm'
    # wp = WavProc()
    # sr, duration, channel_num, wav_data = wp.pcm_read(wav_path, 8000)
    # print('采样率: %d Hz' % sr)
    # print('长度: %d ms' % round(1000 * duration))
    # print('通道数: %d ch' % channel_num)
