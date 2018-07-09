package com.djxs.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;
/**
 * ������
 * @author LDW
 *
 */
public class CreateQRCode_All {
	public static void main(String[] args) {
		int version = 12;
		//��ά�������
		String content = "http://dijiaxueshe.com";
		String content1 = "dijiaxueshe";
		String content2 = "123456";
		//ͨ��VCard�����ʽʵ����Ƭ��ά��
		String content3 = "BEGIN:VCARD\r\n" + 
						  "PHOTO;VALUE=uri:http://img4.imgtn.bdimg.com/it/u=3630352509,3120025421&fm=27&gp=0.jpg\r\n" + 
						  "N:��;��:���Ϊ;;;\r\n" + 
						  "FN: ��:���Ϊ  ��\r\n" + 
						  "TITLE:java��������ʦ\r\n" + 
						  "ADR;WORK:;;�ػʵ����κ���ڼδ�ý;;;;\r\n" + 
						  "TEL;CELL,VOICE:18603369235\r\n" + 
						  "TEL;WORK,VOICE:0335-1111111\r\n" + 
						  "URL;WORK:http://www.dijiaxueshe.com\r\n" + 
						  "EMAIL;INTERNET,HOME:532231254@qq.com\r\n" + 
						  "END:VCARD";
		String imgPath = "D:/qrcode.png";
		String logoPath = "D:/logo.jpg";
		CreateQRCode_All.generateQRCode(version, content3, imgPath, logoPath , "255,0,0", "0,255,255");
	}
	/**
	 * 
	 * @param version	��ά��汾
	 * @param content	��ά������
	 * @param imgPath	��ά�뱣��·��
	 * @param logoPath	logo·��
	 * @param startRGB	������ʼɫ
	 * @param endRGB	�������ɫ
	 * @return
	 */
	public static int generateQRCode(int version, String content, String imgPath, String logoPath, String startRGB, String endRGB) {
		try {
			//��ά���С
			int imgSize = 67+(version-1)*12;
			//1.����Qrcode����
			Qrcode qrcode = new Qrcode();
			//1.1���ö�ά���Ŵ��ʣ���ѡL(7%)��M(15%)��Q(25%)��H(30%)���Ŵ���Խ�߿ɴ洢����ϢԽ�٣����Զ�ά�������ȵ�Ҫ��ԽС
			qrcode.setQrcodeErrorCorrect('L');
			//1.2���ö�ά��ɴ洢����Ϣ���Ϳ�����N,A,B
			//N:����������
			//A:a-z,A-Z
			//B:�����ַ�
			qrcode.setQrcodeEncodeMode('B');
			//1.3���ö�ά��İ汾��,������1-40,�汾��Խ���ά����԰�������Ϣ��Խ��,��ά��Ҳ��Խ����
			qrcode.setQrcodeVersion(version);
			//2.��ȡͼƬ������
			BufferedImage bufferedImage = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_BGR);
			//3.��ȡ��ͼ����
			Graphics2D gs = bufferedImage.createGraphics();
			//3.1���ñ�����ɫ
			gs.setBackground(Color.WHITE);
			//���ö�ά��Ҫ��������
			//��ɫ�����Զ���
			//3.2����ǰ����ɫ
			gs.setColor(Color.BLACK);
			//3.3�����������
			gs.clearRect(0, 0, imgSize, imgSize);
			//��ȡ��ά���Ӧλ���Ƿ�Ҫ�����ɫ
			boolean[][] calQrcode = qrcode.calQrcode(content.getBytes("utf-8"));
			//����ƫ����
			int pixoff = 2;
			
			int startR=0,startG=0,startB=0;
			if (null != startRGB) {
				String [] rgb = startRGB.split(",");
				startR = Integer.valueOf(rgb[0]);
				startG = Integer.valueOf(rgb[1]);
				startB = Integer.valueOf(rgb[2]);
			}
			int endR=0,endG=0,endB=0;
			if(null != startRGB) {
				String [] rgb = endRGB.split(",");
				endR = Integer.valueOf(rgb[0]);
				endG = Integer.valueOf(rgb[1]);
				endB = Integer.valueOf(rgb[2]);
			}
			
			for (int i = 0; i < calQrcode.length; i++) {
				for (int j = 0; j < calQrcode.length; j++) {
					if (calQrcode[i][j]) {
						int num1 = startR+(endR-startR)*(i+1)/calQrcode.length;
						int num2 = startG+(endG-startG)*(i+1)/calQrcode.length;
						int num3 = startB+(endB-startB)*(i+1)/calQrcode.length;
						if (num1 > 255) {
							num1 = 255;
						}
						if (num2 > 255) {
							num2 = 255;
						}
						if (num3 > 255) {
							num3 = 255;
						}
						/*if(20<=i && 22>=i) {
							continue;
						}
						if(20<=j && 22>=j) {
							continue;
						}*/
						Color color = new Color(num1, num2, num3);
						gs.setColor(color);
						gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);
					}
				}
			}
			//��ȡlogoͷ��
			BufferedImage logo = ImageIO.read(new File(logoPath));
			int logoSize = imgSize/4;
			int o = (imgSize - logoSize)/2;
			//��ͼƬ����������logoͼ��
			gs.drawImage(logo, o, o, logoSize, logoSize, null);
			//�رջ�ͼ
			gs.dispose();
			//��������е���Դ
			bufferedImage.flush();
			//����ͼƬ��ʽ���������·��
			ImageIO.write(bufferedImage, "png", new File(imgPath));
			System.out.println("������");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("��ά������ʧ��");
			return -1;
		}
		return 1;
	}
}
