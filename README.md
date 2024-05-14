# Email 사용자 인증 API 

* 🛠️ Stacks : Spring boot<br/><br/>
### 이메일 주소가 유효하지 않은 경우 ✔️ 
 > _이메일 주소 형식에 맞지 않거나, null값인 경우 아래의 결과를 반환합니다._<br/>
 
📍 _**Postman 결과 화면**_
<img width="1160" alt="스크린샷 2024-05-14 오후 12 53 53" src="https://github.com/Hszoo/MailSender/assets/97530721/2a0900db-8ae9-49e8-8e4b-8483715cc7b3"><br/>


### 이메일 주소가 유효한 경우 ✔️ 
> _파라미터로 넘겨받은 이메일 주소로 아래와 같이 인증번호를 전송합니다._<br/>

📍 _**Gmail로 전송받은 인증번호**_

<img width="663" alt="스크린샷 2024-05-14 오후 12 59 16" src="https://github.com/Hszoo/MailSender/assets/97530721/fcb032eb-e474-4908-aaf3-f0390ee8a375">

📍 _**Postman 결과 화면**_
* _**인증번호가 정상적으로 전송된 경우, 암호화된 인증번호와 함께 전송 성공 메시지가 반환됩니다.**_
<img width="1162" alt="스크린샷 2024-05-14 오후 1 02 23" src="https://github.com/Hszoo/MailSender/assets/97530721/54ca5874-4f08-482e-9529-e713ab90b6b1">
<br/><br/>

### 암호화 알고리즘 ✔️ 
> SHA-256알고리즘으로 인증번호를 암호화합니다.<br/>
``` JAVA
  MessageDigest md = MessageDigest.getInstance("SHA-256");
  md.update(bytes);

  byte[] byteData = md.digest();

  StringBuffer sb = new StringBuffer();
  for (int i = 0; i < byteData.length; i++) {
      sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
  }

  result = sb.toString();
```
