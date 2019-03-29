package com.juk.msap.microsvc.web;

import com.juk.msap.microsvc.service.MSService;
import com.juk.msap.microsvc.util.CommUtil;
import com.juk.msap.microsvc.util.JsonUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MSController {
    @Autowired
    private MSService mService;
    @Autowired
    private CommUtil commUtil;
    @Autowired
    private JsonUtil jsonUtil;

    //MS -> API G/W로는 response객체 stream에 write하여 응답
    @RequestMapping("/acps/v1/user/name") //하나만 매핑하는경우
    public void getUserName(HttpServletRequest request, HttpServletResponse response) {
        //API G/W 호출 URL: https://도커도메인:port/acps/v1/user/name
        //JSON객체:{"UID":"값"}
        //Http reqeust에서 stream으로 data를 받기->API명추출/json변환->MS 라우팅조회/전달
        System.out.println("0. call getAcpsUserTotal : " + request.getRequestURI());

        //1.json 추출
        //사용자 요청 data(json) 추출
        JSONObject jsonObj = jsonUtil.extractReqJson(request);

        //1-2)data추출 (서비스마다 정의된 스펙에 따라 달라짐)
        String userId = (String) jsonObj.get("UID");

        //2.DB 조회 및 결과받아옴
        String result = mService.getUserName(userId);

        processPostJob(request, response, result);

        System.out.println("3. sendResultToUser 응답완료!");
    }

    @RequestMapping("/acps/v1/user/indate") //하나만 매핑하는경우
    public void getUserInsertDate(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("0. call getUserInsertDate : " + request.getRequestURI());

        //1.json 추출
        //사용자 요청 data(json) 추출
        JSONObject jsonObj = jsonUtil.extractReqJson(request);

        //1-2)data추출 (서비스마다 정의된 스펙에 따라 달라짐)
        String userId = (String) jsonObj.get("UID");

        //2.DB 조회 및 결과받아옴
        String result = mService.getUserNamInsertDate(userId);

        processPostJob(request, response, result);

        System.out.println("3. getUserInsertDate 응답완료!");
    }

    private void processPostJob(HttpServletRequest request, HttpServletResponse response, String result){

        //api method를 추출하여 결과를 json으로 생성
        String[] apiArr = request.getRequestURI().split("/");
        JSONObject resData = new JSONObject();
        if(result!=null && result.length()>0) {
            resData.put(apiArr[apiArr.length - 1], result);
        }else{
            resData.put(apiArr[apiArr.length - 1], "");
        }
        System.out.println("2. 응답객체 생성완료: " + resData.toJSONString());

        //3.response객체 steam에 write
        //Controller의 반환값을 주면, ViewResolver동작으로 오류가 발생함으로 return값 없음
        commUtil.sendResultToUser(response, resData.toJSONString());
    }
}
