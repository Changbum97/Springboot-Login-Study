# SpringBoot Login 관련 기능 Study

## 구현해 본 로그인 방법

1. Cookie를 사용한 로그인
2. Session을 사용한 로그인
3. Spring Security를 사용한 로그인 (Form Login)

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
   - Thymeleaf, Validation을 사용한 Global Error 출력
3. 로그아웃 기능 구현
4. 유저 정보 출력 기능 구현
   - 인증(Authentication)된 유저만 접근 가능
   - 유저의 loginId, nickname, role 출력
5. 관리자 페이지 구현
   - 인가(Authorization) 기능 구현
   - role이 "ADMIN"인 유저만 관리자 페이지 접근 가능

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

