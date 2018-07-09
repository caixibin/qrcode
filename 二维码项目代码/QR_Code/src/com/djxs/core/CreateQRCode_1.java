package com.djxs.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRCode_1 {
	public static void main(String[] args) throws IOException {
		int v =6;
		int width = 127;
		int height = 127;
		Qrcode x = new Qrcode();
		  	x.setQrcodeErrorCorrect('L');
	        x.setQrcodeEncodeMode('B');//ע��汾��Ϣ N�������� ��A���� a-z,A-Z��B���� ����)
	        x.setQrcodeVersion(v);//�汾��  1-40
	        String qrData = "http://www.dijiaxueshe.com";//������Ϣ

	        byte[] contentBytes = qrData.getBytes("utf-8");//����ת��ʽ��Ҫ�׳��쳣

	        //������
	        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

	        //��ͼ
	        Graphics2D gs = bufferedImage.createGraphics();

	        gs.setBackground(Color.WHITE);
	        gs.setColor(Color.BLACK);
	        gs.clearRect(0, 0, width, height);

	        //ƫ����
	        int pixoff = 2;


	        /**
	         * ���ײȿӵĵط�
	         * 1.ע��forѭ�������i��j��˳��
	         *   s[j][i]��ά�����j��i��˳��Ҫ����������е� gs.fillRect(j*3+pixoff,i*3+pixoff, 3, 3);
	         *   ˳��ƥ�䣬�������ֽ���ͼƬ��һ������
	         * 2.ע����ж�if (d.length > 0 && d.length < 120)
	         *   �Ƿ�������ַ������ȴ���120�������ɴ��벻ִ�У���ά��հ�
	         *   �����Լ����ַ�����С�����ô�����
	         */
	        if (contentBytes.length > 0 && contentBytes.length < 125) {
	            boolean[][] s = x.calQrcode(contentBytes);
	            for (int i = 0; i < s.length; i++) {
	                for (int j = 0; j < s.length; j++) {
	                    if (s[j][i]) {
	                        gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
	                    }
	                }
	            }
	        }else {
	        	System.err.println("QRCode content bytes length = " + contentBytes.length + " not in [ 0,125]. ");
	        }
	        gs.dispose();//�رջ�ͼ
	        bufferedImage.flush(); //��������е���Դ��,������flush������������ǿ�� ���������ڲ��������Ѿ����������һ����д��.
	        //����ͼƬ��ʽ���������·��
	        ImageIO.write(bufferedImage, "png", new File("D:/qrcode.png"));
	        System.out.println("��ά���������");
		 
	}
}
