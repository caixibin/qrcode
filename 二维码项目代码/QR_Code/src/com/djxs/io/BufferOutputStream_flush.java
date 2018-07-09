package com.djxs.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ���������д������ʱ�Ļ���������
 * 
 * @author adminitartor
 *
 */
public class BufferOutputStream_flush {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("bos.txt");
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		String str = "�㻹Ҫ������,Ҫ����,��ͻȻ���Ķ��ž͹��ұ���.";
		byte[] data = str.getBytes();

		bos.write(data);
		/*
		 * void flush() ��������flush������������ǿ�� ���������ڲ��������Ѿ���������� һ����д��. �������������д�����ݵļ�ʱ��,
		 * ����Ƶ�����ûᵼ��д��������� �Ӷ�����дЧ��.���ʵ��ҵ������ ���Ӧ��.
		 */
		bos.flush();
		System.out.println("д�����!");

		bos.close();
	}
}
