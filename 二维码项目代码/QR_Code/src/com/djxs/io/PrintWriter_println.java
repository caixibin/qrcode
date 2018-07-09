package com.djxs.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * �����ַ��� BufferedWriter,BufferedReader �����ַ������ص��ǿ��԰��ж�д�ַ���,�ڲ� ά��������,���Զ�Ч��Ҳ��.
 * 
 * PrintWriterҲ�ǻ����ַ���,���һ�֧���Զ� ��ˢ�¹���,�ڲ�����BufferedWriter��Ϊ�� ���幦��.
 * 
 * @author adminitartor
 *
 */
public class PrintWriter_println {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		/*
		 * PW�ṩ��ֱ������ļ�д������ ���췽�� PrintWriter(File file) PrintWriter(String path)
		 * 
		 * Ҳ���԰���ָ���ַ���д���ַ��� PrintWriter(File file,String csn) PrintWriter(String
		 * path,String csn)
		 * 
		 */
		PrintWriter pw = new PrintWriter("pw.txt", "UTF-8");
		pw.println("ҹ������������,");
		pw.println("�ܶ�����,");
		pw.println("����������,�ĵ׵Ĺ¶���̾Ϣ.");
		System.out.println("д�����!");
		pw.close();
	}
}
