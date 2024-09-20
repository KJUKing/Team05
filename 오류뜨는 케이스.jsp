<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="kr.or.ddit.member.vo.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Our Website</title>
</head>
<body>
<h2>Welcome to Our Website</h2>

<%
    //HTTP Status 405 – "Method Not Allowed" 오류는
    // 클라이언트가 서버로 잘못된 HTTP 메서드를 요청했을 때 발생합니다.
    // 즉, 특정 URL이 POST 요청을 지원하지 않는데, 클라이언트가
    // 그 URL로 POST 요청을 보냈기 때문에 발생하는 오류입니다.

        /*
        서블릿 메서드 확인: 서블릿에서 doPost() 메서드가 구현되어 있는지 확인하세요.
        만약 doPost()가 구현되지 않았거나, 잘못된 메서드로 요청이 들어왔을 경우 405 오류가 발생할 수 있습니다.

        폼의 method 속성 확인: JSP나 HTML에서 작성된 폼이 method="post"로 설정되어 있는지 확인하세요.
        만약 폼이 GET 방식으로 전송되고 있고 서블릿이 doGet() 메서드를 처리하지 않도록 설정되어 있다면
        405 오류가 발생할 수 있습니다.

        URL과 서블릿 매핑 확인: 요청이 해당 서블릿으로 올바르게 매핑되었는지 확인하세요.
        web.xml이나 애노테이션을 통해 적절하게 매핑되었는지 검토하세요.

        클라이언트 측 문제 확인: AJAX 요청 등에서 잘못된 HTTP 메서드를 사용하고 있을 수 있으니
        해당 부분도 점검이 필요합니다.

        ### 문제의 원인

DB에 데이터는 정상적으로 삽입되었으나, 삽입 후 405 오류가 발생한 이유는 **HTTP 메서드 처리 방식**과 관련된 문제였습니다. 이 문제는 주로 **포워딩 방식**(`request.getRequestDispatcher()`)과 **리다이렉션 방식**(`response.sendRedirect()`) 간의 차이에서 발생합니다.

### 해결 흐름 요약

1. **POST 요청으로 서블릿에 데이터 전송**: 사용자가 폼을 제출하면, 데이터가 POST 방식으로 `InsertNoticeController`에 전달되고, 여기서 DB에 공지사항 데이터가 성공적으로 삽입됩니다.

2. **공지사항 리스트로 이동**: 데이터 삽입 후 공지사항 리스트 페이지로 이동해야 하는데, 이때 `request.getRequestDispatcher("/main?view=noticeList")`를 사용해 포워딩하고 있었습니다.

3. **405 오류 발생 이유**:
   - `request.getRequestDispatcher()`를 사용하면 **현재 요청 객체**를 그대로 유지한 채 다른 페이지로 포워딩합니다.
   - 즉, 처음에 `doPost()`로 들어온 POST 요청이 `noticeList` 페이지로 이동할 때도 여전히 **POST 메서드**로 처리되기 때문에, 최종적으로 **프론트컨트롤러**가 POST 요청을 받게 됩니다.
   - 그러나 **프론트컨트롤러는 POST 요청을 처리하지 않고 GET 요청만 처리하도록 설정**되어 있어서 405 오류가 발생한 것입니다.

### `response.sendRedirect()`의 역할

`response.sendRedirect()`를 사용하면 **새로운 요청**을 만들어 **클라이언트에게 리다이렉션**을 요청합니다. 이 과정에서:
- **클라이언트(브라우저)**가 서버로 새롭게 **GET 요청**을 보내므로, POST 요청이 더 이상 유지되지 않습니다.
- 이로 인해 프론트컨트롤러가 GET 요청을 받아서 문제없이 처리할 수 있게 됩니다.

#### 리다이렉션의 흐름:
1. 클라이언트가 POST 요청을 보냄 → 서버에서 데이터를 처리하고 `response.sendRedirect()` 호출.
2. 서버는 클라이언트에게 **GET 요청을 새롭게 보내라**고 응답.
3. 클라이언트는 서버로 새로 **GET 요청**을 보냄.
4. 프론트컨트롤러는 GET 요청을 처리하여 `noticeList.jsp`로 이동.

이 과정을 통해 POST 요청을 끝내고, 새로운 GET 요청으로 페이지를 전환하게 됩니다. **POST/REDIRECT/GET(P/R/G)** 패턴이라고 부릅니다.

### `request.getRequestDispatcher()`와 `response.sendRedirect()`의 차이점

- **`request.getRequestDispatcher()`**:
  - 서버 내부에서 요청을 **포워딩**하는 방식으로, 클라이언트는 URL이 바뀌지 않음.
  - **같은 요청 객체**가 전달되기 때문에, 메서드 타입(POST/GET)이 유지됨.
  - 따라서, POST 요청으로 포워딩할 경우 **여전히 POST 요청**이 유지되어 이후 GET 처리만 가능한 리소스(프론트컨트롤러)가 POST 요청을 받으면 405 오류 발생.

- **`response.sendRedirect()`**:
  - 서버가 **클라이언트에게 새로운 요청을 보내도록** 응답하는 방식.
  - 클라이언트는 새로 **GET 요청**을 보내므로 요청 메서드가 **POST에서 GET으로 전환**됨.
  - URL도 클라이언트 쪽에서 갱신되며, POST 요청 후에는 새로운 GET 요청을 통해 페이지가 전환되므로 안전하게 페이지 전환이 이루어짐.

### 프론트컨트롤러에서의 유연한 처리 가능성

프론트컨트롤러에서 유연하게 POST 요청을 처리하는 방법은 있지만, 보통은 POST 요청 이후에는 **리다이렉트**하는 것이 권장됩니다. 그 이유는:
- POST 요청 후 새로고침을 하거나 URL로 접근할 경우, 다시 POST 요청이 발생해 **데이터가 중복으로 저장되는 문제**를 방지하기 위해서입니다.
- POST 요청 후에 `response.sendRedirect()`를 통해 GET 요청으로 전환하는 방식이 일반적인 **P/R/G 패턴**입니다.

하지만 만약 **프론트컨트롤러에서 POST 요청을 처리하고 싶다면** 다음과 같은 방법을 사용할 수 있습니다.

1. **프론트컨트롤러에 `doPost()` 메서드 추가**:
   프론트컨트롤러에서 POST 요청을 처리할 수 있도록 `doPost()` 메서드를 추가하면 POST 요청도 유연하게 처리할 수 있습니다. 그러나 권장되는 방식은 아닙니다.

   ```java
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       CartVO viewName = request.getParameter("view");
       // POST 요청을 처리하거나 GET 요청으로 리다이렉트할 수 있음
       response.sendRedirect(request.getContextPath() + "/main?view=" + viewName);
   }
   ```

2. **POST 요청 후 바로 리다이렉트하는 구조 유지**:
   데이터 처리 후 새로운 GET 요청을 클라이언트에게 보내는 `sendRedirect()` 방식이 가장 일반적이며, 이 패턴을 유지하는 것이 일반적으로 좋은 방법입니다.

### 결론

- **오류 원인**: `request.getRequestDispatcher()`는 요청을 그대로 유지하며, POST 요청을 그대로 전달하여 405 오류가 발생.
- **`sendRedirect()`가 필요한 이유**: POST 요청 후 클라이언트에게 GET 요청으로 전환시켜, 안전하게 새 페이지로 이동하기 위해.
- **프론트컨트롤러의 처리**: POST 요청을 굳이 처리하지 않고, GET 요청으로 전환하는 방식이 일반적인 웹 애플리케이션 설계 패턴입니다.

        240916 해결과정

        내가 글쓰기를하고 나서 리스트가 제대로 출력안됐을때
        글 작성하기 버튼을 완료하면 내가 마지막으로 쓴 글이 반영돼서 리스트에 바로떠야하는데 아무것도안뜨는경우

        클라이언트는 POST로 요청을 보냈고 서블릿에서 doPost로 처리를한다
        근데 [프론트 컨트롤러]를 거치고 난 뒤에 jsp를 가려하려는데
        프론트 컨트롤러는 doGet만 처리하고있어서 getRequestDispatcher 으로 보내면 405오류가발생한다

        request.getRequestDispatcher("/main?view=noticeList").forward(request, response);

        서블릿자체는 doPost로 요청을 처리했기때문에 계속 doPost상태인데 Get방식으로 전환하고 프론트컨트롤러로 보내면
        doPost - doGet이렇게 서로 안맞는상태에서 충돌이나버리기때문에 405오류가 발생함.

        그래서 sendRedirect로 doPost를 종료시키고 doGet으로 전환시켜서 새로시작해야한다

        1. response.sendRedirect(request.getContextPath() + "/main?view=noticeList");


        2.              case "noticeList":
                    page = "/WEB-INF/views/notice/noticeList.jsp";
                    break;

        3. if (request.getAttribute("noticeList") != null) {
                List<NoticeVO> noticeList = (List<NoticeVO>) request.getAttribute("noticeList");
                for (NoticeVO notice : noticeList) {
        이런방식으로 프론트컨트롤러를 거쳐서 jsp를 가면 당연히 jsp에는 request.getAttribute("noticeList")에 아무런
        값도 담겨있지가 않다

        그러므로 처음부터 sendRedirect를 프론트컨트롤러로 잡는게아니라 서블릿으로 포커싱한다

        1. response.sendRedirect(request.getContextPath() + "/notice/noticeList.do");
            이렇게하면 index에서 처음에서 리스트를 잘불러오듯
            어차피서블릿에서 처리하고 난 뒤에는 자연스럽게 프론트컨트롤러로 호출한다음 jsp로가기때문에 이게 정답이다

        즉 데이터흐름을 잘읽어야한다 언제 메인레이아웃을 씌울지 리다이렉트할때 데이터가 다사라지지는않는지
        경우의 수가 꽤나 많기때문에 정형화된 케이스라고 말하긴 힘들고 각기 어떤 모델을 만드냐에따라 유연하게 사고할필요가있다



         */




%>

</body>
</html>