## Java Config 방식(no .xml) springMVC Project
### 2020-01-17

* 1. web.xml을 대신할 projectInit.java Class를 생성
- AbstractAnnotationConfigDispatcherServletInitializer을 extends 하기

* 2. root-context.xml을 대신할 RootConfig.java Class를 생성
- @Configuration을 클래스에 지정
- method는 없는 상태

* 3. servlet-context.xml을 대신할 WebServletConfig.java Class를 생성
- @Configuration을 클래스에 지정
- @EnableWebMvc를 클래스에 지정
- @ComponentScan을 클래스에 지정하고 controller와 service package를 배열로 설정

- addResourceHandlers() method를 구현하는 코드 추가
- InternalResourceViewResolver를 생성하고 mapping을 설정하는 코드 추가
- 