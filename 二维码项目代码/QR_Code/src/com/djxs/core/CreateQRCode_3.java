package com.djxs.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;
/**
 * ���ɶ�ά��(QRCode)ͼƬ��logo
 * @author Administrator
 *
 */
public class CreateQRCode_3 {
	/**  
     * @param content ��ά��ͼƬ������ 
     * @param imgPath ���ɶ�ά��ͼƬ������·�� 
     * @param logoPath  ��ά��ͼƬ�м��logo·�� 
     */    
    public static int createQRCode(String content, String imgPath,String logoPath,int version) {    
        try {    
            Qrcode qrcodeHandler = new Qrcode();    
            //���ö�ά���Ŵ��ʣ���ѡL(7%)��M(15%)��Q(25%)��H(30%)���Ŵ���Խ�߿ɴ洢����ϢԽ�٣����Զ�ά�������ȵ�Ҫ��ԽС    
            qrcodeHandler.setQrcodeErrorCorrect('M');    
            //N��������,A�����ַ�a-Z,B���������ַ�  
            qrcodeHandler.setQrcodeEncodeMode('B');   
            // �������ö�ά��汾��ȡֵ��Χ1-40��ֵԽ��ߴ�Խ�󣬿ɴ洢����ϢԽ��    
            qrcodeHandler.setQrcodeVersion(version);   
            // ͼƬ�ߴ�    
            int imgSize =67 + 12 * (version - 1) ;  
    
            byte[] contentBytes = content.getBytes("utf-8");    
            BufferedImage image = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);    
            Graphics2D gs = image.createGraphics();    
    
            gs.setBackground(Color.WHITE);    
            gs.clearRect(0, 0, imgSize, imgSize);  //����»�������  
    
            // �趨ͼ����ɫ > BLACK    
            gs.setColor(Color.BLACK);    
    
            // ����ƫ���� �����ÿ��ܵ��½�������    
            int pixoff = 2;    
            // ������� > ��ά��    
            if (contentBytes.length > 0 && contentBytes.length < 130) {  
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
                for (int i = 0; i < codeOut.length; i++) {  
                    for (int j = 0; j < codeOut.length; j++) {  
                        if (codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
                        }  
                    }  
                }  
            } else {    
                System.err.println("QRCode content bytes length = "    
                        + contentBytes.length + " not in [ 0,125]. ");    
                return -1;  
            }    
            Image logo = ImageIO.read(new File(logoPath));//ʵ����һ��Image����  
            int logoSize = imgSize/4; 
             /** 
               * logo�������� 
              */  
            int o = (imgSize - logoSize) / 2;  
            gs.drawImage(logo, o, o, logoSize, logoSize, null);  
            gs.dispose();    
            image.flush();    
    
            // ���ɶ�ά��QRCodeͼƬ    
            File imgFile = new File(imgPath);    
            ImageIO.write(image, "png", imgFile);
            System.out.println("��ά���������");
    
        } catch (Exception e)   
        {    
            e.printStackTrace();    
            return -1;  
        }    
          
        return 0;  
    }    
  
  
    public static void main(String[] args) {  
	    String imgPath = "D:/��ά������/logo_QRCode.png";   
	    String logoPath = "D:/logo.jpg";  
	    String encoderContent = "http://www.dijiaxueshe.com";  
	    int createQRCode = CreateQRCode_3.createQRCode(encoderContent, imgPath, logoPath,12);  
	    if(createQRCode == -1) {
	    	System.out.println("���ɶ�ά��ʧ��");
	    }
    }
}  
