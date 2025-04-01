package com.kh.start.token.model.service;

import java.util.Map;

public interface TokenService {

    // 1. AccessToken만들기
    // 2. RefreshToken 만들기
    Map<String, String> generateToken(String username);

    // 3. RefreshToken Table에 INSERT하기
    

    // 4. 만료기간이 끝난 리프레시토큰 DELETE하기

    // 5. 사용자가 리프레시토큰을 가지고 증명하려 할 때 DB가서 조회해오기

}
