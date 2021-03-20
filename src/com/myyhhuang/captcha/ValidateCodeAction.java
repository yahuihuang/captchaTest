package com.myyhhuang.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidateCodeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public void init() throws ServletException     
    {     
        super.init();     
    }   
        
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException     
    {  
        // 設定響應的型別格式為圖片格式  
        response.setContentType("image/jpeg");  
        //禁止影象快取。  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
      
        HttpSession session = request.getSession();  
      
        ValidateCode vCode = new ValidateCode();  
        session.setAttribute("code", vCode.getCode());  
        vCode.write(response.getOutputStream()); 
    }
  
    public void destroy()     
    {     
    }      
}
