package com.juk.msap.web;

import com.juk.msap.dao.ApiGWDao;
import com.juk.msap.service.ApiGWService;
import com.juk.msap.test.TestVO;
import com.juk.msap.util.CommUtil;
import com.juk.msap.util.JsonUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;

@Controller //@RestController = @Controller + json응답(3.x버전:@ResponseBody선언필요, 4.x:기본json응답)
public class ApiGWContorller {
    @Autowired
    private CommUtil commUtil;
    @Autowired
    private JsonUtil jsonUtil;
    @Autowired
    private ApiGWService apiService;

    //요청사용자의 response객체 steam에 결과를 write하여 응답전송함
    @RequestMapping({"/*", "/*/*", "/*/*/*", "/*/*/*/*"}) //다중매핑인 경우(msap/~~)
    public void routeMicroService(HttpServletRequest request, HttpServletResponse response
            , @ModelAttribute("test") TestVO testVO ){
        //사용자 호출 URL: https://도메인/msap/acps/v1/user/name/
        //JSON DATA는 request객체의 stream으로 전달: {"UID":"FFTEST0001","":""}
        System.out.println("0, call routeMicroService : " + request.getRequestURI());

        //1-1)Http reqeust에서 stream으로 data를 받기->API명추출/json변환->MS 라우팅조회/전달
        //사용자 요청 data(json) 추출
        JSONObject jsonObj = jsonUtil.extractReqJson(request);

        //1-2) URI에서 MS API명 추출->대상port 조회
        String MS_CON_HOST = request.getParameter("MS_CON_HOST");
        String USER_ID = request.getParameter("USER_ID");
        String MS_CON_PORT = request.getParameter("MS_CON_PORT");
        String msApiBaseUrl = null;
        if(MS_CON_HOST!=null && MS_CON_HOST.length()>0){ msApiBaseUrl = MS_CON_HOST; }
        else{ msApiBaseUrl = "http://127.0.0.1"; }

        String uri = request.getRequestURI();
        String extractApiUri = uri.substring(uri.indexOf("/msap")+5); //"/acps/v1/uers/name";
        String API_WEB_PORT = null; // "10001";
        API_WEB_PORT = apiService.getApiPort(extractApiUri);
        if(MS_CON_PORT!=null && MS_CON_PORT.length()>0){
            API_WEB_PORT = MS_CON_PORT; //테스트용
        }

        //2.MS API URL로 data전달(호출)
        String msUrl = msApiBaseUrl + ":"+ API_WEB_PORT + extractApiUri;
        HttpURLConnection httpConn = commUtil.deliveryMicroService(msUrl, jsonObj, USER_ID);

        //3.응답결과를 버퍼저장
        StringBuffer msRespData = new StringBuffer();
        commUtil.recvMSRespData(httpConn, msRespData);

        //4. 사용자응답객체에 write
        commUtil.sendResultToUser(response, msRespData);
        System.out.println("4. sendResultToUser 응답완료!");
    }
}