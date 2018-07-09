
package com.djxs.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * ���ɶ�ά��(QRCode)ͼƬ��logo�ͽ���
 * 
 * @author Administrator
 *
 */
public class CreateQRCode_4 {
	/**
	 * @param content
	 *            ��ά��ͼƬ������
	 * @param imgPath
	 *            ���ɶ�ά��ͼƬ������·��
	 * @param logoPath
	 *            ��ά��ͼƬ�м��logo·��
	 */
	public static int createQRCode(String content, String imgPath, String logoPath, int version , String startRgb, String endRgb) {
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// ���ö�ά���Ŵ��ʣ���ѡL(7%)��M(15%)��Q(25%)��H(30%)���Ŵ���Խ�߿ɴ洢����ϢԽ�٣����Զ�ά�������ȵ�Ҫ��ԽС
			qrcodeHandler.setQrcodeErrorCorrect('L');
			// N��������,A�����ַ�a-Z,B���������ַ�
			qrcodeHandler.setQrcodeEncodeMode('B');
			// �������ö�ά��汾��ȡֵ��Χ1-40��ֵԽ��ߴ�Խ�󣬿ɴ洢����ϢԽ��
			qrcodeHandler.setQrcodeVersion(version);
			// ͼƬ�ߴ�
			int imgSize = 67 + 12 * (version - 1);
			// logo�ߴ�
			int logoSize = imgSize / 4;

			byte[] contentBytes = content.getBytes("utf-8");
			BufferedImage image = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = image.createGraphics();

			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize); // ����»�������

			// �趨ͼ����ɫ > BLACK
			gs.setColor(Color.BLACK);
			// ����ƫ���� �����ÿ��ܵ��½�������
			int pixoff = 2;
			int startR = 0,startG = 0,startB = 0;
			if(null != startRgb) {
				String[] rgb = startRgb.split(",");
				startR = Integer.valueOf(rgb[0]);
				startG = Integer.valueOf(rgb[1]);
				startB = Integer.valueOf(rgb[2]);
			}
			int endR = 255,endG = 255,endB = 255;
			if(null != endRgb) {
				String[] rgb = endRgb.split(",");
				endR = Integer.valueOf(rgb[0]);
				endG = Integer.valueOf(rgb[1]);
				endB = Integer.valueOf(rgb[2]);
			}
			// ������� > ��ά��
			if (contentBytes.length > 0 && contentBytes.length < 1000) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {//������
					for (int j = 0; j < codeOut.length; j++) {//������
						if (codeOut[j][i]) {
							if (codeOut[j][i]) {
								int num1 = (int) (startR + (endR - startB) / logoSize * (j + 1));
								int num2 = (int) (startG + (endG - startG) / logoSize * (j + 1));
								int num3 = (int) (startB + (endB - startB) / logoSize * (j + 1));
								if (num1 > 255) {
									num1 = 255;
								}
								if (num2 > 255) {
									num2 = 255;
								}
								if (num3 > 255) {
									num3 = 255;
								}
								Color color = new Color(num1, num2, num3);
								gs.setColor(color);
								gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
							}
						}
					}
				}
			} else {
				System.err.println("QRCode content bytes length = " + contentBytes.length + " not in [ 0,1000]. ");
				return -1;
			}
			Image logo = ImageIO.read(new File(logoPath));// ʵ����һ��Image����
			
			//logo��������
			int o = (imgSize - logoSize) / 2;
			gs.drawImage(logo, o, o, logoSize, logoSize, null);
			gs.dispose();
			image.flush();

			//���ɶ�ά��QRCodeͼƬ
			File imgFile = new File(imgPath);
			ImageIO.write(image, "png", imgFile);
			System.out.println("��ά���������");

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	public static void main(String[] args) {
		String imgPath = "D:/��ά������/logo_QRCode.png";
		String logoPath = "D:/logo.jpg";
		//ͨ��VCard�����ʽʵ����Ƭ��ά��
		String content = "BEGIN:VCARD\r\n" + 
						  "N:��;��:���Ϊ;;;\r\n" + 
						  "FN: ��:���Ϊ  ��\r\n" + 
						  "TITLE:java��������ʦ\r\n" + 
						  "ADR;WORK:;;�ػʵ����κ���ڼδ�ý;;;;\r\n" + 
						  "TEL;CELL,VOICE:18603369235\r\n" + 
						  "TEL;WORK,VOICE:0335-1111111\r\n" + 
						  "URL;WORK:http://www.dijiaxueshe.com\r\n" + 
						  "EMAIL;INTERNET,HOME:532231254@qq.com\r\n" + 
						  "END:VCARD";
		int createQRCode = CreateQRCode_4.createQRCode(content, imgPath, logoPath, 12, "50,155,0", "100,200,50");
		if (createQRCode == -1) {
			System.out.println("���ɶ�ά��ʧ��");
		}
	}
}
