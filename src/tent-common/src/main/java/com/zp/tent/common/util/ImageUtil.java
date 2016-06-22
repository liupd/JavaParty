package com.zp.tent.common.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtil {

    /**
     * @param imageData JPG 图像文件
     * @param waterMarkFile 水印图片
     * @return 加水印后的图像数据
     * @throws IOException
     */
    public static byte[] waterMark(byte[] imageData, String waterMarkFile)
            throws IOException {

        // 水印图片的右边距 下边距
        int paddingRight = 10;
        int paddingBottom = 10;

        // 原始图像
        Image image = new ImageIcon(imageData).getImage();
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);

        // 水印图片
        Image waterMark = ImageIO.read(new File(waterMarkFile));
        int waterMarkWidth = waterMark.getWidth(null);
        int waterMarkHeight = waterMark.getHeight(null);

        // 如果图片尺寸过小，则不打水印，直接返回
        if (imageWidth < waterMarkWidth + 2 * paddingRight
                || imageHeight < waterMarkHeight + 2 * paddingBottom) {
            return imageData;
        }
        BufferedImage bufferedImage = new BufferedImage(imageWidth,
                imageHeight, BufferedImage.TYPE_INT_RGB);

        Graphics g = bufferedImage.createGraphics();

        // 绘制原始图像
        g.drawImage(image, 0, 0, imageWidth, imageHeight, null);
        // 绘制水印图片
        g.drawImage(waterMark, imageWidth - waterMarkWidth - paddingRight,
                imageHeight - waterMarkHeight - paddingBottom, waterMarkWidth,
                waterMarkHeight, null);
        g.dispose();

        // 转成JPEG格式
        ByteArrayOutputStream out = new ByteArrayOutputStream();
		// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		// encoder.encode(bufferedImage);
        byte[] data = out.toByteArray();
        out.close();
        return data;
    }
}
