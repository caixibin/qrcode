package com.djxs.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ������ java.io.BufferedInputStream java.io.BufferedOutputStream
 * ��������һ�Ը߼���,ʹ�����ǿ�����߶�д Ч��
 * 
 * @author adminitartor
 *
 */
public class CopyDemo2 {
	public static void main(String[] args) throws IOException {
		FileInputStream src = new FileInputStream("music.mp3");
		BufferedInputStream bis = new BufferedInputStream(src);

		FileOutputStream desc = new FileOutputStream("music_cp2.mp3");
		BufferedOutputStream bos = new BufferedOutputStream(desc);

		int d = -1;
		/*
		 * �������ڲ�ά��һ���ֽ�����,ʵ���� Ҳ��ͨ�����ÿ��ʵ�ʶ�д���ֽ��� ���ٶ�д��������ߵĶ�дЧ��. ����: ������bis.read()������ȡһ���ֽ�ʱ
		 * ʵ����bis��ͨ���ļ���һ���Զ�ȡ���� �ֽڲ������ڲ����ֽ�����,Ȼ��ֻ�� ��һ���ֽڷ���.�������ٴε���read
		 * ������ȡһ���ֽ�ʱ,bis��ֱ�ӽ������� ��һ���ֽڷ���,ֱ�������ֽڶ��Ѿ� ���غ�Ż��ٴζ�ȡһ���ֽڻ���.
		 * 
		 * 
		 */
		while ((d = bis.read()) != -1) {
			bos.write(d);
		}

		System.out.println("�������!");
		bis.close();
		bos.close();
	}
}
