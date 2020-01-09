# 이미지 갤러리 프로젝트
* 2020-01-03

### WYCWYG(What You See What You Get) Editor
* summernote : https://summernote.org/

* textarea에 id값을 설정하고 id에 jquery를 이용해서 속성을 설정해주면
간변하게 WYCWYG 방식으로 문서를 작성할 수 있다.

### Drag and Drop으로 하나의 파일 올리기
1 Drag And Drop을 수행할 box를 만들기
2 jquery에 dragover, drop 이벤트를 설정
3 e.originalEvent.dataTransfer로부터 files 객체를 추출
4 files 객체의 0번째 file 객체를 추출

5 ajax를 사용해서 서버로 파일을 업로드 수행하고
6 파일이름 등을 다시 response로 되돌려 받아서 
7 form의 img_file input box에 저장하고

8 내용을  form post로 upload하면 나머지 정보를 db에 저장 

### Drag And Drop으로 여러개의 파일 올리기
* 1~3 까지는 single file upload와 동일
4. files 객체에 담긴 모든 file 객체를 formData에 append()
5. ajax를 사용해서 server로 upload를 수행하고,
6. Controller는 수신된 파일들을 서버에 저장하고
7. 저장된 파일이름들을 리스트로 생성해서(String[] mFiles)
8. 생성된 파일이름 리스트와 리스트를 표현할 jsp를 rendering하여 다시 return 하고
9. ajax success 코드에서는 return 받은 html 코드를 
10. 리스트를 표현할 box에 보인다.

####이때 리스트를 만들시에
* hidden으로 파일이름리스트를 담을 input box를 만들고
* 각각의 파일이름을 input box의 value에 추가해 둔다.

#### 저장을 하면
* Controller에서는 다시 본문(text)과 함께 hidden input box에 담긴 파일 이름들을 수신해
* String[] mFile 문자열 배열 매개변수로 받아 수신하면 된다.  

### form에서 같은 tag에 다중값을 담아서 controller에 전달하기 img_upload_list.jsp(img_list의 input tag)
#### 1. List<String> 형식으로 전달하기

* form에서 같은 이름의 tag에 다중의 값을 담고
* <input name="title" value="1번">
* <input name="title" value="2번">
* <input name="title" value="3번">

### [1번 방식(ImgGalleryV2 방식)]
* Controller에서 
* String[] title 형식으로 매개변수 설정해서 받을 수 있음
* VO 내부에서 "List<String> title 변수" 를 설정해 두고 VO에 받기


### [2번 방식]

* form에서 같은 이름의 tag에 다중의 값을 담고
* <input name="main[0].title" value="1번">
* <input name="main[1].title" value="2번">
* <input name="main[2].title" value="3번">

### HttpSession 데이터의 흐름
* [HTTP의 StatusLess 특성] 
HTTP(Hyper Text Transfer Protocol)에서 request가 이루어지고 결과를 response하게 되면 
web browser와 server간에 어떠한 정보도 남지 않는 특성

* 통신에서는 같은 client(web browser)에서 같은 주소로 서버에 자주 request를 수행하는 경우가 많다.

* 이때 client가 요청한 request에 대한 정보를 server가 참조하고 싶을 때가 있다. 
(대표적인 예가 로그인한 정보)

* 과거에는 cookie라는 것을 사용해서 사용자가 로그인을 수행하면 
그 사용자의 로그인 정보를 cookie라는 데이터 형태로 만들어서 client에게 보내면,
client는 어딘가에 그 정보를 저장해 두었다가

* 다시 request를 수행할 때 cookie를 request 정보에 담아서 같이 전송을 했다.
* 서버에서는 request 정보에 cookie가 담겨 있으면 그 정보를 분석하여
* client로부터 보내온 정보를 분석하여 행동을 결정했다.
* cookie라는 정보가 보통 암호화 되지 않고, 누구나 열어볼 수 있는 형태가 많다.
즉, 해커가 중간에 정보를 가로채서 cookie정보가 노출되는 등의 형태로 민감한 개인정보의 누출이 매우 쉽다.

* 이러한 단점을 보완하기 위해서 Session이라는 개체 개념이 도입되었고,
Java 기반 서버(WAS)에서는 HttpSession 클래스를 만들어 쉽게 Session을 관리할 수 있도록 하고 있다.

* 서버는 필요할 때 HttpSession객체에 Attribute를 추가하면 Java에서 사용할 수 있는 어떤 데이터라도 
Session 형태로 만들어서 사용할 수 있다.

* httpSession.setAttribute("MEMBER", memberVO) 형식의 코드를 작성하면
memberVO 객체에 담긴 모든 데이터가 서버 기억장치 어딘가에 보관이 되고,
MEMGER라는 이름으로 Session ID가 생성된다.

* 이 Session ID는 자체적으로 특별한 방법을 통해 암호화된 값으로 변환되며,
* 서버에서 response를 수행하면 자동으로 response Body에 이 Session ID값이 추가되어 client로 보내진다.

* 이때 client는 수신된 response 정보에 Session ID가 있으면 cookie영역에 이 ID를 임시보관하며
* 이후에 client에서 request를 보낼 때 이 Session ID를 첨가하여 서버로 보낸다.

* 서버(Spring)에서는 request 정보에 Session ID가 있으면 해당 Session객체를 메모리에서 찾아보고 
* Session ID가 유효하면 그 객체를 controller의 method에 주입한다.

* Controller의 method에서는 HttpSession httpSession형식의 매개변수를 선언해 두면 
해당 객체에 Session 객체 값이 담겨있어 코드에서 사용 가능하다.
 





