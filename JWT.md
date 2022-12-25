테이블 컬럼이 memberId, password로 되어있는데 각자 프로젝트로 구조 변경..


로그인 encode 다른 방식으로 변경
(주석에 올바르지 않은 방법이라고 쓰여있음)
    - 애초에 DB에 넣을때 SHA-256 같은 방법을 사용해서 넣어야 한다는 뜻
      [ 지금은 "1234"로 하드코딩 해놨는데 실제로는 SHA-256으로 이미 들어있을거기 때문 ]
      CustomUserDetailsService 클래스의 createUserDetails 메서드 내용중
        .password(passwordEncoder.encode(member.getPassword()))
      이 부분을
        .password(member.getPassword()) 로 바꾸면됨

이미 로그인 되어있는지 확인하는 방법
    - 로그인 성공시, 페이지 이동시마다 request Header에 Authorization쪽에 (Bearer {AccessToken})
      붙여서 보낼것 같음
      그럼 JwtAuthenticationFilter.java 에서 필터는 request.getHeader("Authorization") 코드로 해석하여 만료시간을 측정하고, 만료됐는지 확인한 후 인증절차 밟음
      

필터 추가 하는 방법,

Token의 종료 시간
    

로그인.
    페이지 이동시마다 request Header에 Authorization쪽에 (Bearer {AccessToken})붙여서 보내기