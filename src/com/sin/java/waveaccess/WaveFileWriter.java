package com.sin.java.waveaccess;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@SuppressWarnings("unused")
public class WaveFileWriter {
	private String filename = null;
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;

	private String chunkdescriptor = "RIFF";
	static private int lenchunkdescriptor = 4;

	private long chunksize = 0;
	static private int lenchunksize = 4;

	private String waveflag = "WAVE";
	static private int lenwaveflag = 4;

	private String fmtsubchunk = "fmt ";
	static private int lenfmtsubchunk = 4;

	private long subchunk1size = 0;
	static private int lensubchunk1size = 4;

	private int audioformat = 0;
	static private int lenaudioformat = 2;

	private int numchannels = 0;
	static private int lennumchannels = 2;

	private long samplerate = 0;
	static private int lensamplerate = 2;

	private long byterate = 0;
	static private int lenbyterate = 4;

	private int blockalign = 0;
	static private int lenblockling = 2;

	private int bitspersample = 0;
	static private int lenbitspersample = 2;

	private String datasubchunk = "data";
	static private int lendatasubchunk = 4;

	private long subchunk2size = 0;
	static private int lensubchunk2size = 4;

	public WaveFileWriter(String filename, int[][] data, long samplerate) {
		this.initWriter(filename, data, 0, data[0].length, samplerate);
	}

	public WaveFileWriter(String filename, int[][] data, int offset, int len,
			long samplerate) {
		this.initWriter(filename, data, offset, len, samplerate);
	}

	public void initWriter(String filename, int[][] data, int offset, int len,
			long samplerate) {
		this.filename = filename;

		try {
			fos = new FileOutputStream(this.filename);
			bos = new BufferedOutputStream(fos);

			// int datalen = data[0].length;
			int datalen = len;

			this.samplerate = samplerate;
			// this.bitspersample = bitspersample;
			this.bitspersample = 16;
			this.numchannels = data.length;
			this.subchunk2size = this.numchannels * (this.bitspersample / 8)
					* datalen;
			this.subchunk1size = 16;
			this.audioformat = 1; // PCM
			this.byterate = this.samplerate * this.bitspersample
					* this.numchannels / 8;
			this.blockalign = this.numchannels * this.bitspersample / 8;

			this.chunksize = this.subchunk2size + 8 + this.subchunk1size + 8
					+ 4;

			writeString(this.chunkdescriptor, lenchunkdescriptor);
			writeLong(this.chunksize);
			writeString(this.waveflag, lenwaveflag);
			writeString(this.fmtsubchunk, lenfmtsubchunk);
			writeLong(this.subchunk1size);
			writeInt(this.audioformat);
			writeInt(this.numchannels);
			writeLong(this.samplerate);
			writeLong(this.byterate);
			writeInt(this.blockalign);
			writeInt(this.bitspersample);
			writeString(this.datasubchunk, lendatasubchunk);
			writeLong(this.subchunk2size);
			for (int i = 0; i < datalen; ++i) {
				for (int n = 0; n < this.numchannels; ++n) {
					if (this.bitspersample == 16) {
						writeInt(data[n][i + offset]);
					} else {
						// writeBytes(data[n], i, 1);
					}
				}
			}
			bos.flush();
			fos.flush();
			bos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {

	}

	private void writeString(String str, int len) {
		if (str.length() != len) {
			throw new IllegalArgumentException("length not match!!!");
		}
		byte[] bt = str.getBytes();
		try {
			bos.write(bt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeInt(int data) {
		byte[] buf = new byte[2];
		buf[1] = (byte) (data >>> 8);
		buf[0] = (byte) (data & 0xFF);
		try {
			bos.write(buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeLong(long data) {
		byte[] buf = new byte[4];
		buf[0] = (byte) (data & 0x00ff);
		buf[1] = (byte) ((data >> 8) & 0x00ff);
		buf[2] = (byte) ((data >> 16) & 0x00ff);
		buf[3] = (byte) ((data >> 24) & 0x00ff);
		try {
			bos.write(buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean saveSingleChannel(String filename, int[] data,
			long samplerate) {
		int[][] datar = new int[1][];
		datar[0] = data;
		WaveFileWriter writer = new WaveFileWriter(filename, datar, samplerate);
		writer.close();
		return true;
	}
}
