package com.modubiz.modu.kona.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.modubiz.modu.common.annotation.UserExcludsionStrategy;
import com.modubiz.modu.common.util.HttpClient;
import com.modubiz.modu.kona.mapper.CommonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Slf4j
@Service
public class CommonService {

    @Value("${kona.host}")
    private String konaHost;

    @Value("${kona.port}")
    private String konaPort;

    @Value("${kona.context.path}")
    private String konaContextPath;

    @Value("${kona.asp.id}")
    private String konaAspId;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    CommonMapper commonMapper;

    /**
     * 코나카드 API요청 메소드(Body, mpaId, apiPath)
     * */
    protected String requestToKonaCard(Object requestBody, String mpaId, String apiPath) throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder().setExclusionStrategies(new UserExcludsionStrategy());
        Gson gson = gsonBuilder.create();
        String body = gson.toJson(requestBody);
        String correlationId = makeCorrelationId();

        log.info("AspId={}", konaAspId);
        log.info("MpaId={}", mpaId);
        log.info("Correlation-Id={}", correlationId);
        log.info(">>Request to Kona={}", body);
        HttpClient http = new HttpClient();
        String strRtn = http.newHeader()
            .setContentType(MediaType.APPLICATION_JSON)
            .setHeader("X-KM-User-AspId", konaAspId)  // 코나카드에서 전달받는 ASP ID
            .setHeader("X-KM-User-MpaId", mpaId)       // ID별 고유 MpaId 값
            .setHeader("X-KM-Correlation-Id", correlationId)   // 전문추적번호
            .Timeout(10)
            .ReadTimeout(10)
            .JsonBody(body)
            .sendPost("http://" + konaHost + ":" + konaPort + konaContextPath + apiPath, "UTF-8")
            .responseBody();

        log.info("<<Response from Kona={}",strRtn);
        return strRtn;
    }


    /**
     * CorrelationId: X-KM-Correlation-Id
     * 코나 Header 전문 추적을 위한 거래번호 생성
     * (Format : 'yyMMddHHmiSS-xxxxxxx')
     * ※ xxxxxxx : 7자리 Hex String
     * */
    private String makeCorrelationId() {
        int numChars = 7;
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numChars){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        SimpleDateFormat yyMMddHHmiSS = new SimpleDateFormat("yyMMddHHmmss");
        String currentDate = yyMMddHHmiSS.format(new Date());

        return currentDate + "-" + sb.toString().substring(0, numChars);
    }

    public String generateUUID(int length) {
        SecureRandom random = new SecureRandom();
        Character[] uuid = new Character[length];

        int success = 0;
        while (success < length) {
            char ch = (char) Math.abs(random.nextLong() % 128);
            if (Character.isLetterOrDigit(ch)) {
                uuid[success] = ch;
                success++;
            }
        }
        return StringUtils.join(uuid);
    }

}
