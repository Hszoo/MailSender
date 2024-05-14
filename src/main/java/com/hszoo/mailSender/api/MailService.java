package com.hszoo.mailSender.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService {
    private final MailManager mailManager;

    public String sendMail(String email) {
        if (!isValidEmail(email) || email.isEmpty()) {
            return "이메일 주소가 유효하지 않습니다.";
        }

        try {
            UUID uuid = UUID.randomUUID(); // 랜덤한 UUID 생성
            String key = uuid.toString().substring(0, 7); // UUID 문자열 중 7자리 -> 인증번호 생성
            String title = "[Spring Email 인증 API] 인증코드 전송";
            String content = "인증 번호 7자리 전송 : " + key;
            mailManager.send(email, title, content);
            key = SHA256Util.getEncrypt(key, email);

            return key;
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace(); // 예외를 출력하거나 로깅할 수 있습니다.
            return null; // 예외가 발생했을 때 반환할 값 또는 예외 상황에 맞게 처리하세요.
        }
    }

    // Email 주소 형식 점검
    private boolean isValidEmail(String email) {
        String emailFormat = "^[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(emailFormat);
    }
}
