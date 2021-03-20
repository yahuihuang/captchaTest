package com.myyhhuang.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;

public class ValidateCode {
	private static final long serialVersionUID = 1L;
	// �Ϥ����e�סC  
    private int width = 160;  
    // �Ϥ������סC  
    private int height = 90;  
    private int fontHeight = 50;
    // ���ҽX�r���Ӽ�  
    private int codeCount = 6;  
    // ���ҽX�z�Z�u��  
    private int lineCount = 150;  
    // ���ҽX  
    private String code = null;  
    // ���ҽX�Ϥ�Buffer  
    private BufferedImage buffImg = null;  
  
    // ���ҽX�d��,�h��0(�Ʀr)�MO(����)�e���V�c��(�p�g��1�ML�]�i�H�h��,�j�g���ΤF)  
    private char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};  

    /** 
     * �w�]�غc�禡,�]�w�w�]�޼� 
     */  
    public ValidateCode() {  
        this.createCode();  
    }  
  
    public void createCode() {  
        int wordWidth = 0; //, codeY = 0;  
        int red = 0, green = 0, blue = 0;  
  
        wordWidth = width / (codeCount + 2);	//�C�Ӧr�����e��(���k�U�ťX�@�Ӧr��) 
        // codeY = fontHeight + (height - fontHeight) / 2; 
        int plusWidth = wordWidth * 2;
        int plusHeight = height - fontHeight;
        int finalWidth = width + wordWidth / 3;
        System.out.println("wordWidth : fontHeight : plusHeight = " + wordWidth + ", " + fontHeight + ", " + plusHeight);
        
        // �v�Hbuffer  
        buffImg = new BufferedImage(width + finalWidth, height, BufferedImage.TYPE_INT_RGB);  
        Graphics2D g = buffImg.createGraphics();  
        // �ͦ��H����  
        Random random = new Random();  
        // �N�v�H��R���զ�  
        g.setColor(Color.WHITE);  
        g.fillRect(0, 0, width + finalWidth, height); 
        System.out.println("width : height = " + width + ", " + height);
  
        for (int i = 0; i < lineCount; i++) {  
            // �]�w�H���}�l�M�����y��  
            int xs = random.nextInt(width);//x�y�ж}�l  
            int ys = random.nextInt(height);//y�y�ж}�l  
            int xe = xs + random.nextInt(width / 8);//x�y�е���  
            int ye = ys + random.nextInt(height / 8);//y�y�е���  
  
            // �����H�����C���,����X���C�Ӥz�Z�u���C��ȳ��N���P�C  
            red = random.nextInt(255);  
            green = random.nextInt(255);  
            blue = random.nextInt(255);  
            g.setColor(new Color(red, green, blue));  
            g.drawLine(xs, ys, xe, ye);  
        }  
        
        // �����H�����C���,����X���C�Ӧr�����C��ȳ��N���P�C 
        /*
        red = random.nextInt(255);  
        green = random.nextInt(255);  
        blue = random.nextInt(255);  
        g.setColor(new Color(red, green, blue));  
        */
        g.setColor(Color.BLACK);
        
        // �إߦr��,�i�H�קאּ�䥦��  
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);  
        // Font font = new Font("Times New Roman", Font.ROMAN_BASELINE, fontHeight);  
        // g.setFont(font); 
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(random.nextInt(50) - 25), 0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);
        g.setFont(rotatedFont); 
  
        // randomCode�O���H�����ͪ����ҽX  
        StringBuffer randomCode = new StringBuffer();  
        // �H������codeCount�Ӧr�������ҽX�C  
        int plusx = random.nextInt(plusWidth);
        int starty = fontHeight + random.nextInt(plusHeight);
        for (int iIdx = 0; iIdx < codeCount; iIdx++) {  
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);  
            
            int startx = iIdx * wordWidth + plusx;
            System.out.println("startx : starty = " + startx + ", " + starty);
            g.drawString(strRand, startx , starty);  
            // �N���ͪ��|���H���ƲզX�b�@�_�C  
            randomCode.append(strRand);  
        }  
        // �N�|��Ʀr�����ҽX�x�s��Session���C  
        code = randomCode.toString();  
    }  
  
    public void write(String path) throws IOException {  
        OutputStream sos = new FileOutputStream(path);  
        this.write(sos);  
    }  
  
    public void write(OutputStream sos) throws IOException {  
        ImageIO.write(buffImg, "png", sos);  
        sos.close();  
    }  
  
    public BufferedImage getBuffImg() {  
        return buffImg;  
    }  
  
    public String getCode() {  
        return code;  
    }  
}
