# 바로고 사전과제

### 기술 스택
Java 11, Spring Boot, H2 Database, Redis, Junit5, Swagger

### TODO LIST
- [X] ERD 설계 (회원, 인증, 주문)
- [X] 프로젝트 구조 설계
- [X] Entity 작성
- [ ] 테스트 케이스 작성
  - [X] 회원가입 성공
  - [X] 회원가입 실패
  - [X] 로그인 성공
  - [X] 로그인 실패
  - [ ] 상품 등록 성공
  - [ ] 상품 조회 성공
  - [ ] 배달 조회 성공
  - [ ] 배달 조회 실패
  - [ ] 배달 목적지 수정 성공
  - [ ] 배달 목적지 수정 실패
  - [ ] 배달 상태 수정 성공
  - [ ] 배달 상태 수정 실패

### ERD 설계

### 프로젝트 구조 설계
```
  src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─barogo
    │  │          └─api
    │  │              ├─domain
    │  │              │  ├─auth
    │  │              │  │  ├─controller
    │  │              │  │  ├─dto
    │  │              │  │  ├─entity
    │  │              │  │  ├─repository
    │  │              │  │  └─service
    │  │              │  ├─delivery
    │  │              │  │  ├─controller
    │  │              │  │  ├─dto
    │  │              │  │  ├─entity
    │  │              │  │  ├─repository
    │  │              │  │  └─service
    │  │              │  ├─order
    │  │              │  │  ├─controller
    │  │              │  │  ├─dto
    │  │              │  │  ├─entity
    │  │              │  │  ├─repository
    │  │              │  │  └─service
    │  │              │  └─user
    │  │              │      ├─controller
    │  │              │      ├─dto
    │  │              │      ├─entity
    │  │              │      ├─exception
    │  │              │      ├─repository
    │  │              │      └─service
    │  │              └─global
    │  │                  ├─common
    │  │                  ├─config
    │  │                  │  ├─jwt
    │  │                  │  └─security
    │  │                  ├─error
    │  │                  └─util
    │  └─resources
    │      └─META-INF
```


### Idea
- 주문, 배송 도메인을 따로 둘 것인가?
