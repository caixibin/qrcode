package com.djxs.io;
/**
 * java�쳣�������
 * try{}��,���������ܳ����쳣�Ĵ���Ƭ��������
 * catch���������񲢽��try���д����׳����쳣
 * 
 * try���ǲ��ܵ��������,����ͨ���ᶨ��catch
 * ��.Ҳ����ֱ�Ӹ�finally�� .
 * @author adminitartor
 *
 */
public class Exception_try_catch {
	public static void main(String[] args) {
		System.out.println("����ʼ��");
		try{
			String str = "a";
			System.out.println(str.length());
			System.out.println(str.charAt(0));
			System.out.println(Integer.parseInt(str));
			//try�г��ִ���Ĵ�����������ݲ���ִ��
			System.out.println("!!!!");
		}catch(NullPointerException e){
			System.out.println("�����˿�ָ��!");
		}catch(StringIndexOutOfBoundsException e){
			System.out.println("�ַ����±�Խ����!");
		
		/*
		 * catch���Զ�����,��Բ�ͬ�쳣��
		 * ��ͬ�����ֶ�ʱ,Ӧ���ֱ𲶻񲢶���
		 * ����ֶ�.
		 * ����Ӧ������һ����ϰ��,�������沶��
		 * Exception,�Է������г���һ��δ֪
		 * �쳣�����³����ж�.
		 * ��������쳣֮����ڼ̳й�ϵʱ,Ӧ��
		 * �Ȳ����������쳣�󲶻������쳣
		 */
		}catch(Exception e){
			System.out.println("�������ǳ��˸���!");
		}
		
		System.out.println("���������");
	}
}






