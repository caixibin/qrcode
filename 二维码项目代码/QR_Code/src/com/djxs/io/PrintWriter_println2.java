package com.djxs.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * PrintWriter���������е�Ӧ��
 * 
 * @author adminitartor
 *
 */
public class PrintWriter_println2 {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		/*
		 * PrintWriter(OutputStream out) PrintWriter(Writer writer)
		 * 
		 */
		FileOutputStream fos = new FileOutputStream("pw2.txt");
		/*
		 * ������������ϣ������ָ���ַ���д��,��Ҫ ��������ת����OSW��ָ���ַ���.
		 */
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		PrintWriter pw = new PrintWriter(osw);

		pw.println("Ħ��Ħ��,��ħ��Ĳ���.");
		pw.println("�ڹ⻬����·�����ϴ���ﻬ!");

		System.out.println("д�����!");
		pw.close();
	}
}
