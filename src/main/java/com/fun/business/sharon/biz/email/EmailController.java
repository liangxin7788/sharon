package com.fun.business.sharon.biz.email;

import com.fun.business.sharon.common.GlobalResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Package: com.fun.business.sharon.biz.email
 * @ClassName: EmailController
 * @Description: 发送邮件示例接口，可根据业务需求进行相应修改
 * @Author: liangxin
 * @CreateDate: 2019/7/1 17:32
 * @UpdateDate: 2019/7/1 17:32
 */
@RestController
@RequestMapping("/email")
@Api(description = "邮件服务")
public class EmailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/testSend")
    @ApiOperation("测试邮件发送")
    public GlobalResult<?> testSend(@RequestBody TextMassageVo vo){
        String resut = mailService.sendSimpleMail(vo.getTo(), vo.getSubject(), vo.getContent());
        return GlobalResult.newSuccess(resut);
    }

    @PostMapping("/testSendFile")
    @ApiOperation("测试带附件邮件发送")
    public GlobalResult<?> testSendFile(@RequestBody FileMassageVo vo) throws Exception {
        String resut = mailService.sendAttachmentsMail(vo.getTo(), vo.getSubject(), vo.getContent(), vo.getFilePaths());
        return GlobalResult.newSuccess(resut);
    }


}
