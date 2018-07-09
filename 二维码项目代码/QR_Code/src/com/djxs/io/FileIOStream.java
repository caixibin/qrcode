package com.djxs.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * java��׼��IO���� IO������Ŀ�������������������ݽ���. �ȿ��Դ�����ȡ����,Ҳ���Խ����ݷ��� �����.
 * java�е�IO��������ʽ����,���ݷ����Ϊ: ������:���ڴ�����������ݵ�����(������) �����:���ڽ����ݷ��������(д����)
 * 
 * java������Ϊ����: �ֽ���,������ �ֽ����ֳ�Ϊ�ͼ���.����ʵ�ڳ���������Դ ֮���"�ܵ�",���������˰�������.��дһ�� ���ڽڵ���.
 * 
 * ������:���������ܶ�������(û����),�������� �ǻ����䴦���������,�򻯶�д����.
 * 
 * java�ṩ����������,�涨�������ֽ��������� �ֽ�������Ĺ��ܶ���.
 * 
 * java.io.InputStream:�����ֽ����������̳� �Ը���.��涨��ʹ����������ȡ�ֽڵ���� ����.
 * 
 * java.io.OutputStream:�����ֽ���������̳� �Ը���,�涨�����������д���ֽڵ���ط���.
 * 
 * 
 * �ļ��� java.io.FileOutputStream java.io.FileInputStream �ļ�����һ�Եͼ���,�����Ƕ�д�ļ��е�����.
 * 
 * @author adminitartor
 *
 */
public class FileIOStream {
	
	public static void main(String[] args) throws IOException {
		out();
		in();
	}
	public static void out() throws IOException {
		/*
		 * ��fos.txt�ļ���д���ַ��� FileOutputStream�ṩ����Ӧ�Ĺ��췽��: FileOutputStream(String path)
		 * FileOutputStream(File file) ��������ֹ��췽���������ļ�������Ǹ��� дģʽ,��:���ļ��Ѵ���,���Ƚ����ļ�������
		 * ���ݶ�ɾ��,Ȼ�󽫱���ͨ������д���������� Ϊ���ļ��е�����.
		 * 
		 * FileOutputStream(String path,boolean append) FileOutputStream(File
		 * file,boolean append) �����ֹ��췽��Ҫ���ڶ��⴫��һ��booleanֵ ����,����ֵΪtrue,���ļ������Ϊ׷��д����
		 * ��:���ļ�����,�Ὣ����ͨ������д�������� ׷�ӵ��ļ�ĩβ.
		 */
		FileOutputStream fos = new FileOutputStream("D:/fos.txt", true);
		fos.write("û���,������ô�Ҷ����.".getBytes());
		System.out.println("д�����!");
		fos.close();

	}

	public static void in() throws IOException {
		FileInputStream fis = new FileInputStream("D:" + File.separator + "fos.txt");
		byte[] data = new byte[200];
		int len = fis.read(data);
		String str = new String(data, 0, len);
		System.out.println(str);
		fis.close();
	}
}
