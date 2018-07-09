package com.djxs.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * ���ɶ�ά��
 */
public class QRCodeUtil {

	// ͼƬ��ȵ�һ��
	private static final int IMAGE_WIDTH = 100;
	private static final int IMAGE_HEIGHT = 100;
	private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
	// ��ά��д����
	private static MultiFormatWriter mutiWriter = new MultiFormatWriter();

	public static void encode(String content, int width, int height, String srcImagePath, String destImagePath, String level, String startRgb, String endRgb) {
		try {
			ImageIO.write(genBarcode(content, width, height, srcImagePath,level,startRgb,endRgb), "jpg", new File(destImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}

	private static BufferedImage genBarcode(String content, int width, int height, String srcImagePath, String level, String startRgb, String endRgb)
			throws WriterException, IOException {
		// ��ȡԴͼ��
		int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
		if (null != srcImagePath) {
			BufferedImage scaleImage = scale(srcImagePath, IMAGE_WIDTH, IMAGE_HEIGHT, true);
			for (int i = 0; i < scaleImage.getWidth(); i++) {
				for (int j = 0; j < scaleImage.getHeight(); j++) {
					srcPixels[i][j] = scaleImage.getRGB(i, j);
				}
			}
		}
		Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
		hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
		
		// ����QR��ά��ľ�����;��Ϊ�ĸ��ȼ���L/M/Q/H ��L--7%,M--15%,Q--25%,H--30%.
		// �ȼ�Խ�ߣ��ݴ���Խ�ߣ�ʶ���ٶȽ��͡�����һ���Ǳ��𻵣��ݴ��ʸߵ�Ҳ���ܹ�ʶ�������ͨ��ΪH
		hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		if("30".equals(level)) {
			hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		}else if("25".equals(level)) {
			hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
		}else if("15".equals(level)) {
			hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		}else if("7".equals(level)) {
			hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		}
		
		
		// ���ɶ�ά��
		BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hint);

		// ��ά����תΪһά��������
		int halfW = matrix.getWidth() / 2;
		int halfH = matrix.getHeight() / 2;

		float startR = 0,startG = 0,startB = 0;
		if(null != startRgb) {
			String[] rgb = startRgb.split(",");
			startR = Float.valueOf(rgb[0]);
			startG = Float.valueOf(rgb[1]);
			startB = Float.valueOf(rgb[2]);
		}
		float endR = 255,endG = 255,endB = 255;
		if(null != endRgb) {
			String[] rgb = endRgb.split(",");
			endR = Float.valueOf(rgb[0]);
			endG = Float.valueOf(rgb[1]);
			endB = Float.valueOf(rgb[2]);
		}
		// ����һ��һάint������ת�������ɫֵ
		int[] pixels = new int[width * height];
		for (int y = 0; y < matrix.getHeight(); y++) {
			for (int x = 0; x < matrix.getWidth(); x++) {

				/**����BitMatrix�е�λֵ������Ӧ���ص����ɫֵ**/
				if (x > halfW - IMAGE_HALF_WIDTH && x < halfW + IMAGE_HALF_WIDTH && y > halfH - IMAGE_HALF_WIDTH
						&& y < halfH + IMAGE_HALF_WIDTH) {
					if(null != srcImagePath) {//����ͼƬ
						pixels[y * width + x] = srcPixels[x - halfW + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];
					}else {
						// ��ά����ɫ
//						int num1 = (int) (50 - (50.0 - 13.0) / matrix.getHeight() * (y + 1));
//						int num2 = (int) (165 - (165.0 - 72.0) / matrix.getHeight() * (y + 1));
//						int num3 = (int) (162 - (162.0 - 107.0) / matrix.getHeight() * (y + 1));
						int num1 = (int) (startR - (startR - endR) / matrix.getHeight() * (y + 1));
						int num2 = (int) (startG - (startG - endG) / matrix.getHeight() * (y + 1));
						int num3 = (int) (startB - (startB - endB) / matrix.getHeight() * (y + 1));
						Color color = new Color(num1, num2, num3);
						int colorInt = color.getRGB();
						// �˴������޸Ķ�ά�����ɫ,���Էֱ��ƶ���ά��ͱ�������ɫ;
						pixels[y * width + x] = matrix.get(x, y) ? colorInt : 16777215;// 0x000000:0xffffff
					}
				} else {
					// ��ά����ɫ
//					int num1 = (int) (50 - (50.0 - 13.0) / matrix.getHeight() * (y + 1));
//					int num2 = (int) (165 - (165.0 - 72.0) / matrix.getHeight() * (y + 1));
//					int num3 = (int) (162 - (162.0 - 107.0) / matrix.getHeight() * (y + 1));
					
					int num1 = (int) (startR - (startR - endR) / matrix.getHeight() * (y + 1));
					int num2 = (int) (startG - (startG - endG) / matrix.getHeight() * (y + 1));
					int num3 = (int) (startB - (startB - endB) / matrix.getHeight() * (y + 1));
					Color color = new Color(num1, num2, num3);
					int colorInt = color.getRGB();
					// �˴������޸Ķ�ά�����ɫ,���Էֱ��ƶ���ά��ͱ�������ɫ;
					pixels[y * width + x] = matrix.get(x, y) ? colorInt : 16777215;// 0x000000:0xffffff
				}

			}
		}

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		image.getRaster().setDataElements(0, 0, width, height, pixels);
		return image;

	}

	/**
	* �Ѵ����ԭʼͼ�񰴸߶ȺͿ�Ƚ�������,���ɷ���Ҫ���ͼ��
	*
	* @param srcImageFile
	* Դ�ļ���ַ
	* @param height
	* Ŀ��߶�
	* @param width
	* Ŀ����
	* @param hasFiller
	* ��������ʱ�Ƿ���Ҫ����:trueΪ����; falseΪ������; * @throws IOException
	*/
	private static BufferedImage scale(String srcImageFile, int height, int width, boolean hasFiller)
			throws IOException {
		double ratio = 0.0; // ���ű���
		File file = new File(srcImageFile);
		BufferedImage srcImage = ImageIO.read(file);
		Image destImage = srcImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);

		// �������
		if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
			if (srcImage.getHeight() > srcImage.getWidth()) {
				ratio = (new Integer(height)).doubleValue() / srcImage.getHeight();
			} else {
				ratio = (new Integer(width)).doubleValue() / srcImage.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
			destImage = op.filter(srcImage, null);
		}

		if (hasFiller) {// ����
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphic = image.createGraphics();
			graphic.setColor(Color.white);
			graphic.fillRect(0, 0, width, height);
			if (width == destImage.getWidth(null)) {
				graphic.drawImage(destImage, 0, (height - destImage.getHeight(null)) / 2, destImage.getWidth(null),
						destImage.getHeight(null), Color.white, null);
			} else {
				graphic.drawImage(destImage, (width - destImage.getWidth(null)) / 2, 0, destImage.getWidth(null),
						destImage.getHeight(null), Color.white, null);
			}
			graphic.dispose();
			destImage = image;
		}
		return (BufferedImage) destImage;
	}

	public static void main(String[] args) throws UnsupportedEncodingException { // ����Ϊ����(��֧������),��,��,�м�ͼ��·��,����·��
		QRCodeUtil.encode("http://www.baidu.com/", 512, 512, "D:\\tx.png", "D:\\2013-01.jpg","30","255,255,0","0,255,255");
	}
}
