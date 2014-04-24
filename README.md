# WaveAccess

波形文件(.wav)的Java读写操作，目前只支持16bit的PCM编码方式。




## API说明
### WaveFileReader

波形读操作封装

* isSuccess() 是否创建wav读取器成功
* getBitPerSample() 获取音频采样位速，16bit或8bit
* getSampleRate() 获取采样率（每秒采样次数）
* getNumChannels() 获取音频声道数，1位单声道，2为立体声
* getDataLen() 获取数据长度
* getData() 获取数据，返回n*m的二维数组，代表n声道数据，m是数据长度

* readSingleChannel() 静态方法，直接返回单声道波形文件数据



## 部分运行截图
* 读取并绘制16位单声道PCM编码波形文件

> ![image](https://raw.githubusercontent.com/sintrb/WaveAccess/master/doc/screenshots/shots_wav_40_16_1_pcm.png)
