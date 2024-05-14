package com.hszoo.mailSender.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;
    // 이메일 인증
    @PostMapping("/sendMail")
    @ResponseBody
    public String SendMail(@RequestParam String email) throws Exception {
        return mailService.sendMail(email);
    }

    @PostMapping("/checkMail")
    @ResponseBody
    public boolean CheckMail(String key, String insertKey,String email) throws Exception {
        insertKey = SHA256Util.getEncrypt(insertKey, email);

        if(key.equals(insertKey)) {
            return true;
        }
        return false;
    }
}
