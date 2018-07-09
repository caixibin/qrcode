package com.djxs.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
/**
 * java.io.File File���ÿһ��ʵ�������ڱ�ʾ����ϵͳ�� �ļ�ϵͳ���һ���ļ���Ŀ¼ ʹ��File����: 1:�����ļ���Ŀ¼��������Ϣ
 * 2:�����ļ���Ŀ¼(����,ɾ��) 3:����һ��Ŀ¼�е����� ���ǲ��ܷ����ļ�����.
 * 
 * @author adminitartor
 *
 */
public class File_info {
	public static void main(String[] args) {
		try {
			File file = new File("D:/test.txt");
			// ��ȡ�ļ���
			String name = file.getName();
			System.out.println("�ļ���:" + name);
			// �����ļ��Ĵ�С(�ֽ���)
			long length = file.length();
			System.out.println("��С:" + length + "�ֽ�");
			
			boolean canRead = file.canRead();
			boolean canWrite = file.canWrite();
			System.out.println("�ɶ�:" + canRead);
			System.out.println("��д:" + canWrite);
			boolean isHidden = file.isHidden();
			System.out.println("�Ƿ�����:" + isHidden);
			
			// ����޸����� 2017��6��28��,9:29:42
			long time = file.lastModified();
			System.out.println(time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy��M��d��,H:m:s");
			String str = sdf.format(time);
			System.out.println(str);
			
			/*
			 * boolean exists()
			 * �жϵ�ǰFile��ʾ���ļ���Ŀ¼�Ƿ�
			 * �Ѿ�����
			 */
			if(!file.exists()){
				file.createNewFile();
				System.out.println("�������!");
			}else{
				System.out.println("���ļ��Ѵ���!");
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
