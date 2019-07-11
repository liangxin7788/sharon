package com.fun.business.sharon.shiro.login.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @Package: com.fun.business.sharon.shiro.login.kaptcha
 * @ClassName: VcodeController
 * @Description: 生成验证码
 * @Author: liangxin
 * @CreateDate: 2019/7/9 11:55
 * @UpdateDate: 2019/7/9 11:55
 */
@Controller
@RequestMapping("/code")
public class VcodeController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    /**
     * Kaptcha 验证码
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @GetMapping("/defaultKaptcha")
//    @ApiOperation(value = "获取验证码",notes = "获取验证码",produces="application/octet-stream")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream gifgOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            System.out.println("Kaptcha ====>>>>" + createText);
            httpServletRequest.getSession().setAttribute("Kaptcha", createText);

            System.out.println("存入Vcode到Session====>" + httpServletRequest.getSession().getAttribute("Kaptcha"));
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "gif", gifgOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            System.out.println("获取验证码异常=======>>>>>>>" + e.getMessage());
            return;
        }

        //定义response输出类型为image/jpeg/gif类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = gifgOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/gif");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

}
