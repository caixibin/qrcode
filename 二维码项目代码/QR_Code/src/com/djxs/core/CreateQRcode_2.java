package com.djxs.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


import com.swetake.util.Qrcode;
/**
 * ��ȡ����
 * @author Administrator
 *
 */
public class CreateQRcode_2 {
	public static void main(String[] args) {
		String content = "http://www.dijiaxueshe.com";
		int version = 10;
		String imgPath = "D:/qrCode12.png";
		int createQRCode = createQRCode(content, imgPath, version);
		if(createQRCode>0) {
			System.out.println("���ɳɹ�");
		}else {
			System.out.println("����ʧ��");
		}
		
	}
	/**  
     * ���ɶ�ά��(QRCode)ͼƬ  (logo)
     * @param content ��ά��ͼƬ������ 
     * @param imgPath ���ɶ�ά��ͼƬ������·�� 
     * @param version  ��ά��ͼƬ�汾
     */   
	public static int createQRCode(String content, String imgPath, int version) {
		try {
			int width = 67+(version-1)*12;
			int height = 67+(version-1)*12;
			//1.����Qrcode����
			//1.1 ���ö�ά���Ŵ���
			//1.2���ö�ά��ɴ洢����Ϣ����
			//1.3���ö�ά��İ汾��
			Qrcode qrcode = new Qrcode();
			qrcode.setQrcodeErrorCorrect('L');
			qrcode.setQrcodeEncodeMode('B');
			qrcode.setQrcodeVersion(version);
			
			byte[] bytes = content.getBytes("utf-8");
			//2.��ȡͼƬ������
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			//3.��ȡ��ͼ����
			Graphics2D gs = bufferedImage.createGraphics();
			//3.1���ñ�����ɫ
			gs.setBackground(Color.WHITE);
			//3.2����ǰ����ɫ
			gs.setColor(Color.BLACK);
			//3.3�����������
			gs.clearRect(0, 0, width, height);
			
			//����ƫ����
			int pixoff = 2;
			//4.����ά��
			
			//4.1�ж϶�ά���Ӧ�������Ƿ������ɫ
			boolean[][] codeOut = qrcode.calQrcode(bytes);
			for (int i = 0; i < codeOut.length; i++) {
				for (int j = 0; j < codeOut.length; j++) {
					if (codeOut[j][i]) {
						//4.2����ά���Ӧ��λ�������ɫ
						gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);
					}
				}
			}
			//�رջ�ͼ(�ͷ���Դ)
			gs.dispose();
			
			//��������е���Դ
			bufferedImage.flush();
			
			File imgfile = new File(imgPath);
			//5.����ͼƬ��ʽ,�������·��
			ImageIO.write(bufferedImage, "png", imgfile);
			System.out.println("��ά���������");
		} catch (Exception e) {
			e.getStackTrace();
			return -100;
		}
		return 1;		
	}
}
