# Memory-Card
## _DAY01_
### 환경설정
1. 개발 환경 : Intellij
2. JDK-Version : 11.0.13
3. Tomcat-Version : 9.0.58-windows-x64
4. Maven-Version : 3.8.4

### Tomcat 연동
1. Settings - Plugins - 'Smart Tomcat' 설치 - 환경설정에서 설치한 Tomcat 서버 경로 지정 - Configurations 설정


| Configurations 설정 | 실행 화면 |
|:--------|:--------:|
| ![Configurations](https://user-images.githubusercontent.com/54324782/155696124-5d09f1ea-68d2-431b-b698-8637c1b71e56.png) | ![실행 화면](https://user-images.githubusercontent.com/54324782/155696194-a78af4e6-3bac-448b-9a34-1ec8834c4161.png)

-----------------------

## _DAY02_
### web.xml 수정
1. 프로젝트 실행 시 index.jsp를 호출하도록 설정
```jsp
<welcome-file-list>
  <welcome-file>index.jsp</welcome-file>
</welcome-file-list>
```

### Servlet 설정 수정
1. 서블릿(Servlet) : Java에서 동적 웹 프로젝트를 개발할 때, 사용자의 요청과 응답을 처리해주는 역할
```jsp
<servlet>
  <servlet-name>action</servlet-name>
  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  <init-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/config/*-servlet.xml</param-value>
  </init-param>
  <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
  <servlet-name>action</servlet-name>
  <url-pattern>*.do</url-pattern>
</servlet-mapping>
```
  - url-pattern : Servlet에 어떠한 요청을 할 때, '.do'를 통해서만 요청을 전달한다.

### MVC 패턴
1. MVC : 사용자 인터페이스와 비지니스 로직을 분리하여 웹 개발한다.
    - Model : 애플리케이션의 정보, 즉 데이터를 나타낸다.
    - View : 사용자에게 보여주는 인터페이스, 즉 화면을 의미한다. (= JSP)
    - Controller : 비지니스 로직과 모델의 상호동작의 조정 역할을 한다.

### Spring 라이브러리 추가
1. pom.xml에 필요한 라이브러리 추가
  - Spring, AspectJ, MyBatis, JDBC, MariaDB, Servlet, Logging, JSON View 등등 ..
  - <org.springframework-version> : Spring 버전 지정
  - <repositories> : 실제 라이브러리를 다운받을 저장소  
  - <dependencies> : 실제 라이브러리를 지정
  
### UTF-8 Encoding 지정 (web.xml)  
```jsp
  <filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <url-pattern>*.do</url-pattern>
  </filter-mapping>
```
  
### Spring 설정파일 추가
1. 특정 폴더에 있는 context-로 시작하는 모든 xml파일을 읽어온다.
```jsp
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath*:config/spring/context-*.xml</param-value>
  </context-param>
```

-----------------------

## _DAY03_
### Log4j 설정
- Log4j는 JAVA 기반의 로깅 유틸리티로, Apache에서 만든 오픈소스 라이브러리다.
- Log4j는 시스템의 성능에 큰 영향을 미치지 않으면서도, 옵션 설정을 통해서 다양한 로깅 방법을 제공한다.
- Log4j 구조
  > Logger : 출력할 메시지를 Appender에 전달한다.  
  > Appender : 전달된 로그를 어디에 출력할지 결정한다. (콘솔 출력, 파일 기록, DB 저장 등)  
  > Layout : 로그를 어떤 형식으로 출력할지 결정한다.  

1. Log4j 라이브러리 추가 및 log4j.xml 작성

### 인터셉터 (Interceptor) 설정
- 인터셉터는 Spring에서 중간에 요청을 가로채서 어떠한 일을 하는 것을 의미한다.
- 인터셉터는 DispatcherServlet이 컨트롤러를 호출하기 전, 후에 요청과 응답을 가로채서 가공할 수 있도록 해준다.
  > DispatcherServlet : 사용자(클라이언트)의 요청을 받아 해당 요청에 매핑되는 컨트롤러와 연결한 후, 컨트롤러에서 정의된 view를 사용자의 브라우저에 출력하는 역할을 수행  
  
1. LoggerInterceptor.java 파일 생성
  - HandlerInterceptorAdaptor 클래스를 상속받아 전처리기와 후처리기를 생성한다.
    > 전처리기 : 클라이언트 -> 컨트롤러 요청 시  
    > 후처리기 : 컨트롤러 -> 클라이언트 응답 시  
  - action-servlet에 인터셉터 설정
  
2. 테스트
```java
@Controller
public class CommonController {
    Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value = "/main_page.do")
    public ModelAndView main_page(Map<String, Object> commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("index");
        log.debug("인터셉터 테스트");

        return mv;
    }
}
  
```
  - @Requestmapping : 프로젝트가 실행될 주소를 의미
  - ModelAndView : 보여줄 view 설정
![Interceptor](https://user-images.githubusercontent.com/54324782/157184908-8ebe89ea-3057-49cc-9698-27dfc5eb5937.png)

-----------------------

## _DAY04_
### Mybatis 연동
  - 기존의 JDBC를 이용하여 프로그래밍을 하는 방식에 비해 Mybatis는 개발자의 부담을 굉장히 많이 덜아주고, 생산성 향상에도 도움이 된다.
  - 기존의 JDBC를 통해 SQL의 변경 등이 발생할 경우, 프로그램(java 파일)을 수정하기 때문에 그 유연성이 좋지 못한 반면에 MyBatis에서는 SQL을 xml 파일에 작성하기 때문에 SQL의 변환이 자유롭고, 가독성이 좋다.
  
1. Mybatis 연결 설정 (데이터베이스 접속)
2. Mybatis와 Spring 연결
```xml
   <bean id="sqlSession" class="org.mybatis.spring.sqlSessionFactoryBean">
       <property name="dataSource" ref="dataSource" />
       <property name="mapperLocations" value="classpath:/mapper/**/*_SQL.xml" />
   </bean>

   <bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
       <constructor-arg index="0" ref="sqlSession" />
   </bean>
```
  
