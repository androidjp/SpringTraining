package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vo.JsonResult;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 登录 相关API 控制器
 */
@Controller
@RequestMapping("/login")
public class LoginController {


    ///获取图片验证码
    @GetMapping("/image/get")
    public void getVerifyImage(HttpSession session, HttpServletResponse response) throws IOException {
        BufferedImage img = new BufferedImage(68,22,BufferedImage.TYPE_INT_ARGB);
        Graphics g  =  img.getGraphics();
        Color c = new Color(231, 232, 227);
        g.setColor(c);
        g.fillRect(0,0,68,22);
        char[] chs = ("qwertyuioplkjhgfdsazxcvbnm").toUpperCase().toCharArray();
        Random r = new Random();

        int len = chs.length;
        int index;
        StringBuilder sb = new StringBuilder();

        for(int  i=0;i<4;i++){
            index = r.nextInt(len);
            char ch = chs[index];
            g.setColor(new Color(r.nextInt(88),r.nextInt(188), r.nextInt(255)));
            g.drawString(ch+"",i*13+3, 18);///绘制
            sb.append(ch);///保存
        }

        session.setAttribute("verify_code", sb.toString());
        ImageIO.write(img,"JPG",response.getOutputStream());
    }

    ///图片验证
    @GetMapping("/image/verify")
    @ResponseBody
    public JsonResult imageVerify(HttpSession session, String verifyCode){
        String msg = (String) session.getAttribute("verify_code");
        System.out.println(msg+"....." + verifyCode);
        if(msg!=null&& verifyCode!=null&&verifyCode.equals(msg)){
            //验证成功
            return new JsonResult(200,"验证成功",null);
        }else{
            //验证失败
            return new JsonResult(200,"验证失败",null);
        }
    }


    @RequestMapping("/test")
    public String test(){
        return "suc";
    }


}
