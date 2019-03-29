<%--
  Created by IntelliJ IDEA.
  User: yelran
  Date: 2018-12-02
  Time: 오후 5:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>ApiGateWay</title>
    <script type="text/javascript">
        function submitForm(sType){
            document.getElementById("form").action = "http://localhost:8086/msap/acps/v1/user/" + sType;
            console.log(document.getElementById("form").action);
            document.getElementById("form").submit();
        }
    </script>
  </head>
  <body>
    <div>API GateWay Test</div><br>

    <form id = "form" method="POST">
        <div>
            host설정: <input type="input" name="MS_CON_HOST" size="30"/>
            port설정: <input type="input" name="MS_CON_PORT" size="5" />
        </div>
        <br>
        <div>
            사용자ID 입력 : <input type="input" name="USER_ID" size="20"/>
            <input type="button" value="이름조회" onclick="submitForm('name');">
            <input type="button" value="입력날짜 조회" onclick="submitForm('indate');">
        </div>
    </form>
  </body>
</html>
