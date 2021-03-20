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
	// 圖片的寬度。  
    private int width = 160;  
    // 圖片的高度。  
    private int height = 90;  
    private int fontHeight = 50;
    // 驗證碼字元個數  
    private int codeCount = 6;  
    // 驗證碼干擾線數  
    private int lineCount = 150;  
    // 驗證碼  
    private String code = null;  
    // 驗證碼圖片Buffer  
    private BufferedImage buffImg = null;  
  
    // 驗證碼範圍,去掉0(數字)和O(拼音)容易混淆的(小寫的1和L也可以去掉,大寫不用了)  
    private char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};  

    /** 
     * 預設建構函式,設定預設引數 
     */  
    public ValidateCode() {  
        this.createCode();  
    }  
  
    public void createCode() {  
        int wordWidth = 0; //, codeY = 0;  
        int red = 0, green = 0, blue = 0;  
  
        wordWidth = width / (codeCount + 2);	//每個字元的寬度(左右各空出一個字元) 
        // codeY = fontHeight + (height - fontHeight) / 2; 
        int plusWidth = wordWidth * 2;
        int plusHeight = height - fontHeight;
        int finalWidth = width + wordWidth / 3;
        System.out.println("wordWidth : fontHeight : plusHeight = " + wordWidth + ", " + fontHeight + ", " + plusHeight);
        
        // 影象buffer  
        buffImg = new BufferedImage(width + finalWidth, height, BufferedImage.TYPE_INT_RGB);  
        Graphics2D g = buffImg.createGraphics();  
        // 生成隨機數  
        Random random = new Random();  
        // 將影象填充為白色  
        g.setColor(Color.WHITE);  
        g.fillRect(0, 0, width + finalWidth, height); 
        System.out.println("width : height = " + width + ", " + height);
  
        for (int i = 0; i < lineCount; i++) {  
            // 設定隨機開始和結束座標  
            int xs = random.nextInt(width);//x座標開始  
            int ys = random.nextInt(height);//y座標開始  
            int xe = xs + random.nextInt(width / 8);//x座標結束  
            int ye = ys + random.nextInt(height / 8);//y座標結束  
  
            // 產生隨機的顏色值,讓輸出的每個干擾線的顏色值都將不同。  
            red = random.nextInt(255);  
            green = random.nextInt(255);  
            blue = random.nextInt(255);  
            g.setColor(new Color(red, green, blue));  
            g.drawLine(xs, ys, xe, ye);  
        }  
        
        // 產生隨機的顏色值,讓輸出的每個字元的顏色值都將不同。 
        /*
        red = random.nextInt(255);  
        green = random.nextInt(255);  
        blue = random.nextInt(255);  
        g.setColor(new Color(red, green, blue));  
        */
        g.setColor(Color.BLACK);
        
        // 建立字型,可以修改為其它的  
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);  
        // Font font = new Font("Times New Roman", Font.ROMAN_BASELINE, fontHeight);  
        // g.setFont(font); 
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(random.nextInt(50) - 25), 0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);
        g.setFont(rotatedFont); 
  
        // randomCode記錄隨機產生的驗證碼  
        StringBuffer randomCode = new StringBuffer();  
        // 隨機產生codeCount個字元的驗證碼。  
        int plusx = random.nextInt(plusWidth);
        int starty = fontHeight + random.nextInt(plusHeight);
        for (int iIdx = 0; iIdx < codeCount; iIdx++) {  
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);  
            
            int startx = iIdx * wordWidth + plusx;
            System.out.println("startx : starty = " + startx + ", " + starty);
            g.drawString(strRand, startx , starty);  
            // 將產生的四個隨機數組合在一起。  
            randomCode.append(strRand);  
        }  
        // 將四位數字的驗證碼儲存到Session中。  
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
