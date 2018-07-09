package com.djxs.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;

public class ReadQRCode {
	   public static void main(String[] args) throws IOException {
	        //ͼƬ·��
	        File file = new File("D:/qrcode.png");
	        //��ȡͼƬ��������
	        BufferedImage bufferedImage = ImageIO.read(file);
	        //QRCode������
	        QRCodeDecoder codeDecoder = new QRCodeDecoder();
	        /**
	         *codeDecoder.decode(new MyQRCodeImage())
	         *������Ҫʵ��QRCodeImage�ӿڣ��Ʋ����һ�δ���
	         */
	        //ͨ��������ά������Ϣ
	        String result = new String(codeDecoder.decode(new MyQRCodeImage(bufferedImage)), "utf-8");
	        System.out.println(result);
	    }
}
