package com.sin.java.waveaccess.test;

// 关于JavaPlot查看https://github.com/sintrb/JavaPlot
import com.sin.java.plot.Plot;
import com.sin.java.plot.PlotFrame;
import com.sin.java.waveaccess.WaveFileReader;

/**
 * 
 * @author RobinTang
 * 
 */
public class TestMain {

	// int 数组 转换到 double数组
	// JavaPlot 只支持double数组的绘制
	public static double[] Integers2Doubles(int[] raw) {
		double[] res = new double[raw.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = raw[i];
		}
		return res;
	}

	// 绘制波形文件
	public static void drawWaveFile(String filename) {
		WaveFileReader reader = new WaveFileReader(filename);
		//
		String[] pamss = new String[] { "-r", "-g", "-b" };
		if (reader.isSuccess()) {
			PlotFrame frame = Plot.figrue(String.format("%s %dHZ %dBit %dCH", filename, reader.getSampleRate(), reader.getBitPerSample(), reader.getNumChannels()));
			frame.setSize(500, 200);
			Plot.hold_on();
			for (int i = 0; i < reader.getNumChannels(); ++i) {
				// 获取i声道数据
				int[] data = reader.getData()[i];
				// 绘图
				Plot.plot(Integers2Doubles(data), pamss[i % pamss.length]);
			}
			Plot.hold_off();
		} else {
			System.err.println(filename + "不是一个正常的wav文件");
		}
	}

	public static void main(String[] args) {
		drawWaveFile("rawwavs/wav_40_16_1_pcm.wav");
		drawWaveFile("rawwavs/wav_40_16_2_pcm.wav");
		
		drawWaveFile("rawwavs/wav_20_8_1_pcm.wav");
		drawWaveFile("rawwavs/wav_20_8_2_pcm.wav");
	}
}



