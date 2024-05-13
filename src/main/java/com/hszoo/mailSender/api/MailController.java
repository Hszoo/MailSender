package com.hszoo.mailSender.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MailController {
    private final MailManager mailManager;

    // 이메일 인증
    @PostMapping("/sendMail")
    @ResponseBody
    public String SendMail(@RequestParam String email) throws Exception {
        if (email == null || email.isEmpty()) {
            return "이메일 주소가 유효하지 않습니다.";
        }
        UUID uuid = UUID.randomUUID(); // 랜덤한 UUID 생성
        String key = uuid.toString().substring(0, 7); // UUID 문자열 중 7자리 -> 인증번호 생성
        String sub ="성주가 성주에게 메일전송";
        String con = "인증 번호 : " + key;
        mailManager.send(email,sub,con);
        key = SHA256Util.getEncrypt(key, email);
        return key;
    }

    @PostMapping("/checkMail") //
    @ResponseBody
    public boolean CheckMail(String key, String insertKey,String email) throws Exception {
        insertKey = SHA256Util.getEncrypt(insertKey, email);

        if(key.equals(insertKey)) {
            return true;
        }
        return false;
    }
}
