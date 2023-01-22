# SpringBoot Login 관련 기능 Study

## 구현해 본 로그인 방법

1. Cookie를 사용한 로그인
2. Session을 사용한 로그인
3. Spring Security를 사용한 로그인 (Form Login)
4. Spring Security를 사용한 로그인 (Jwt Token API Login)
5. JWT + Cookie를 사용한 로그인 (Jwt Token Page Login)
6. OAuth 2.0 로그인 (구글)

## 구현 기능

1. 회원가입 기능 구현
   - loginId, password, passwordCheck, nickname을 입력 받아 회원가입 진행
   - loginId와 nickname이 중복되면 회원가입 진행 X
   - password와 passwordCheck가 다르면 회원가입 진행 X
   - Thymeleaf, Validation을 사용한 Field Error 출력
   - 회원가입 시 권한(role)은 "USER"로 설정
2. 로그인 기능 구현
   - loginId, password를 입력 받아 로그인 진행
   - loginId가 존재하지 않거나 password가 틀렸으면 로그인 진행 X
   - Thymeleaf, Validation을 사용한 Global Error 출력 또는 로그인 페이지로 redirect
3. 로그아웃 기능 구현
4. 유저 정보 출력 기능 구현
   - 인증(Authentication)된 유저만 접근 가능
   - 유저의 loginId, nickname, role 출력
5. 관리자 페이지 구현
   - 인가(Authorization) 기능 구현
   - role이 "ADMIN"인 유저만 관리자 페이지 접근 가능
6. 공통 화면 사용
   - 모든 페이지는 공통으로 사용
   - 화면에 로그인 방식 출력과 각각의 로그인에 맞는 URL 매핑을 위해 HTML 페이지에 loginType, pageName 전송
   - @ControllerAdvice를 활용하여 API 로그인이 아닌 경우에 URL을 통해 어떤 로그인 예제인지 체크 후 해당하는 loginType, pageName을 model에 담아 전송

## Cookie를 사용한 로그인 구현

- 로그인 성공 시 userId를 Cookie에 담아서 발급
- 인증, 인가가 필요한 페이지 접근 시 Cookie에서 userId를 꺼내 인증, 인가 진행

## Session을 사용한 로그인 구현

- 로그인 성공 시 HttpSession에 Session 생성 후 Key("userId"), Value(userId) 삽입
- 인증, 인가가 필요한 페이지 접근 시 Session에서 userId를 꺼내 인증, 인가 진행
- 세션 리스트 확인 기능 구현

## Spring Security를 사용한 로그인 구현 (Form Login)

- Spring Security의 BCryptPasswordEncoder을 사용한 비밀번호 암호화
- SecurityConfig 설정을 통한 인증, 인가, 로그인, 로그아웃 진행
- Spring Security의 로그인에 필요한 PrincipalDetails, PrincipalDetailsService 구현
- authenticationEntryPoint을 사용한 인증 실패 페이지 출력 구현
- accessDeniedHandler을 사용한 인가 실패 페이지 출력 구현

## Spring Security를 사용한 로그인 구현 (Jwt Token Login)

- JwtTokenFilter와 SecurityFilterChain을 사용해 Jwt Token Login 구현
- JwtTokenUtil 기능
  - 로그인 성고 시 Jwt Token 생성
  - 인증, 인가 진행 시 Jwt Token에서 유저 정보 추출
  - Jwt Token의 유효 시간이 지났는지 체크
- Security Form Login에서 사용한 SecurityConfig와 Jwt Token Login에서 사용한 SecurityConfig2 충돌 발생
  - 둘 중 하나는 주석 처리를 통해 등록하지 않고 사용해야 함

## JWT + Cookie를 사용한 로그인 구현

- Front-End 없이 JWT 방식으로 화면 로그인을 구현하기 위해 Cookie 사용
- 로그인 성공 시 Jwt Token 발급 => 쿠키에 넣어서 전송
- 인증, 인가 시 쿠키에서 Jwt Token을 추출해 인증, 인가 진행
- 로그아웃 시 쿠키 파기

## OAuth 2.0 로그인

- 구글, 카카오, 네이버, 페이스북 로그인 구현
- Security Form 로그인 예제에 코드를 추가, 수정하며 구현
- Google Api Client-Id, Client-Secret은 보안을 위해 환경변수로 등록 
- PrincipalOauth2UserService를 통해 OAuth 로그인 및 회원가입 진행
- OAuth2UserInfo를 상속받은 GoogleUserInfo, KakaoUserInfo, NaverUserInfo, FacebookUserInfo 사용
