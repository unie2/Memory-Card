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
