# WaveAccess

波形文件(.wav)的Java读写操作，支持16bit和8bitPCM编码的单双声道文件操作

时间和精力有限，没能对所有的wav格式文件都测试一下。

另外，我测试用的wav文件使用Cool Edit Pro录制的。



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


* 读取并绘制16位立体声PCM编码波形文件

> ![image](https://raw.githubusercontent.com/sintrb/WaveAccess/master/doc/screenshots/shots_wav_40_16_2_pcm.png)

* 读取并绘制8位单声道PCM编码波形文件

> ![image](https://raw.githubusercontent.com/sintrb/WaveAccess/master/doc/screenshots/shots_wav_20_8_1_pcm.png)


* 读取并绘制8位立体声PCM编码波形文件

> ![image](https://raw.githubusercontent.com/sintrb/WaveAccess/master/doc/screenshots/shots_wav_20_8_2_pcm.png)


=

## 怎么获取这些代码？

* 如果你只是想简单的使用这些代码的话你可以把它当作一个压缩包下载到你的电脑上，点击右边的“Download ZIP”:

![image](https://raw.githubusercontent.com/sintrb/forgithub/master/img/screenshots/githubdownloadzip.png)


* 如果你喜欢这些代码，那么你可以加星：

![image](https://raw.githubusercontent.com/sintrb/forgithub/master/img/screenshots/githubstart.png)

* 如果你觉得这些代码还有很多可以改善的地方，那么请先fork一下（欢迎fork）：

![image](https://raw.githubusercontent.com/sintrb/forgithub/master/img/screenshots/githubfork.png)

* fork之后这些代码就变成了你的了，你可以从自己的仓库中把它们clone到你的电脑上，之后的操作就和git一样了：

![image](https://raw.githubusercontent.com/sintrb/forgithub/master/img/screenshots/githubsshclone.png)

**享受开源带来的乐趣吧**

