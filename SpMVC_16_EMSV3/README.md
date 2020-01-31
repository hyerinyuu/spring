# EMS(Email Management Service) ProjectV1
* 2020-01-20

### JPA-Hibernate, MySQL 연동 프로젝트

	<bean id="emsHiber" class="org.apache.commons.dbcp2.BasicDataSource">
		<property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
		<property name="url" value="jdbc:mysql://127.0.0.1:3306/emsDB?serverTimezone=Asia/Seoul" />
		<property name="username" value="ems"/>
		<property name="password" value="ems" />
	</bean>
	
* DriverClassName : com.mysql.cj.jdbc.Driver를 사용한다
* url : 기본 연결 주소에 쿼리값을 추가하는데 serverTimeZone=Asia/Seoul로 설정해줘야함
* ver 5.x에서는 SSL연결을 하지 않겠다는 의미의 & useSSL=false 옵션을 사용해야하지만
* ver 8.x에서는 오류가 발생하니 추가하지 않는다



### naver 아이디로 로그인 구현
1. 네이버에게 login창을 보내달라고 요청
	- 서버에서 state라는 특별한 key를 하나 생성하고 그 값을 같이 보내야한다. 

2. 네이버가 login창을 redirect해주고
3. 네이버가 보내는 login창에 로그인을 수행하고 정상으로 로그인이 완료되면
4. OAuth20규격의 token(버스표)을 보내주는데 이 토큰을 이용해서 
네이버에 어떤 정보(회원정보)들을 요청할 수 있다. 

	