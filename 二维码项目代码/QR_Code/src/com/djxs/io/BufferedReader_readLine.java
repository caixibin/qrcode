package com.djxs.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * java.io.BufferedLine() �����ַ�������,���԰��ж�ȡ�ַ���
 * 
 * @author adminitartor
 *
 */
public class BufferedReader_readLine {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream(
				"src" + File.separator + "day08" + File.separator + "BufferedReader_readLine.java");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		/*
		 * String readLine() ������ȡ�����ַ�,ֱ����ȡ������ ��Ϊֹ,Ȼ�������ַ���һ���ַ��� ����ʽ����.ע��:���ص�����ַ���
		 * ���ǲ��������Ļ��з���. ���÷�������ֵΪnull,���ʾ��ȡ���� �ļ�ĩβ.������BR��ȡ�����豸ʱ�� ����ֵΪnull��ζ��ͨ��������������
		 * ��ȡ���κ�����.
		 */
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}

		br.close();
	}
}
