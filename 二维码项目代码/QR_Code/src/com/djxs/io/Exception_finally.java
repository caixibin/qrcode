package com.djxs.io;

/**
 * finally�� finally��ֻ�ܳ������쳣������Ƶ���� ��:try����������һ��catch֮��.
 * finally����Ա�֤����try���еĴ����Ƿ� �׳��쳣�ÿ���Ĵ��붼һ����ִ��. ����ͨ���Ὣ���۳����Ƿ����Ҫ���е�
 * �������finally��ȷ��ִ��,���������� �еĹر����Լ������ͷ���Դ�Ȳ���.
 * 
 * @author adminitartor
 *
 */
public class Exception_finally {
	public static void main(String[] args) {
		System.out.println("����ʼ��");

		try {
			String str = "";
			int n = 1 / 0;
			System.out.println(str.length());
		} catch (Exception e) {
			System.out.println("���������");
		} finally {
			System.out.println("finally�еĴ���ִ����!");
		}

		System.out.println("���������");
	}
}
