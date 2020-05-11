package com.absym.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.RandomAccess;


public class CaptchaUtil {

    final private char[] CODE = {
            '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    final private String[] FONTNAME = new String[]{
            "黑体", "宋体", "Courier", "Arial",
            "Verdana", "Times", "Tahoma", "Georgia"};

    final private int[] FONTSTYLE = new int[]{
            Font.BOLD, Font.ITALIC, Font.PLAIN
    };


    private int captchaLen = 4;
    private int fontSize = 21;
    private int width = (fontSize + 1) * fontSize + 10;
    private int height = fontSize + 12;
    private int disturbLine = 3;
    private String captchStr;

    public CaptchaUtil() {
    }

    /**
     * @param captchaLen
     */
    public CaptchaUtil(int captchaLen) {
        this.captchaLen = captchaLen;
        this.width = (fontSize + 1) * fontSize + 10;
    }

    /**
     * @param captchaLen
     * @param width
     * @param height
     */
    public CaptchaUtil(int captchaLen, int width, int height) {
        this.captchaLen = captchaLen;
        this.width = width;
        this.height = height;
    }

    /**
     * @param enableLine
     * @return
     */
    public BufferedImage generatorRotateVCaptchaImage(boolean enableLine) {
        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = image.createGraphics();
        image = graphic.getDeviceConfiguration().createCompatibleImage(this.width, this.height, Transparency.TRANSLUCENT);
        graphic = image.createGraphics();
        if (enableLine) {
            drawDisturbLine(graphic);
        }
        for (int i = 0; i < this.captchaLen; i++) {
            BufferedImage rotateImage = getRotateImage(this.captchStr.charAt(i));
            graphic.drawImage(rotateImage, null, (int) (this.height * 0.7) * i, 0);
        }
        graphic.dispose();
        return image;
    }

    public BufferedImage generatorCaptchaImage(boolean enableLine) {
        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = image.createGraphics();
        image = graphic.getDeviceConfiguration().createCompatibleImage(this.width, this.height, Transparency.TRANSLUCENT);
        graphic = image.createGraphics();
        if (enableLine) {
            drawDisturbLine(graphic);
        }
        Random random = new Random();
        for (int i = 0; i < this.captchaLen; i++) {
            graphic.setFont(new Font(FONTNAME[random.nextInt(FONTNAME.length)],
                    FONTSTYLE[random.nextInt(FONTSTYLE.length)], this.fontSize));
            graphic.setColor(getRandomColor());
            graphic.drawString(this.captchStr.charAt(i) + "", i * this.fontSize + 10, this.fontSize + 5);
        }
        graphic.dispose();
        return image;

    }

    public String generatorStrCaptch() {
        Random random = new Random();
        int len = this.captchaLen;
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < this.captchaLen; i++) {
            int index = random.nextInt(CODE.length);
            buf.append(CODE[index]);
        }
        this.captchStr = buf.toString();
        return this.captchStr;
    }

    public int getCaptchaLen() {
        return captchaLen;
    }

    public void setCaptchaLen(int captchaLen) {
        this.width = (this.fontSize + 3) * captchaLen + 10;
        this.captchaLen = captchaLen;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.width = (fontSize + 3) * this.captchaLen + 10;
        this.height = fontSize + 15;
        this.fontSize = fontSize;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDisturbLine() {
        return disturbLine;
    }

    public void setDisturbLine(int disturbLine) {
        this.disturbLine = disturbLine;
    }

    public String getCaptchStr() {
        return captchStr;
    }

    public void setCaptchStr(String captchStr) {
        this.captchStr = captchStr;
    }

    /**
     * @param character
     * @return
     */
    private BufferedImage getRotateImage(char character) {

        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = image.createGraphics();
        image = graphic.getDeviceConfiguration().createCompatibleImage(this.width, this.height, Transparency.TRANSLUCENT);
        graphic = image.createGraphics();

        Random random = new Random();
        graphic.setFont(new Font(this.FONTNAME[random.nextInt(this.FONTNAME.length)],
                this.FONTSTYLE[random.nextInt(this.FONTSTYLE.length)], this.fontSize));
        graphic.setColor(getRandomColor());
        double angle = getAngle();
        //旋转图片
        graphic.rotate(angle, (double) this.height / 2, (double) this.height / 2);
        graphic.drawString(Character.toString(character), (height - this.fontSize) / 2, this.fontSize + 5);
        graphic.dispose();
        return image;
    }

    /**
     * @return
     */
    private double getAngle() {
        return ((int) (Math.random() * 1000) % 2 == 0 ? -1 : 1) * Math.random();
    }

    /**
     * @return
     */
    private Color getRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(155) + 100,
                random.nextInt(155) + 100,
                random.nextInt(155) + 100);
    }

    /**
     * @param graphic
     */
    private void drawDisturbLine(Graphics graphic) {
        Random ranom = new Random();
        for (int i = 0; i < disturbLine; i++) {
            graphic.setColor(getRandomColor());
            //画干扰线
            graphic.drawLine(ranom.nextInt(width),
                    ranom.nextInt(height),
                    ranom.nextInt(width),
                    ranom.nextInt(height));
        }
    }
}
