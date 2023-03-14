# ✏개발일지

## 2023-03-04
1주차 작업 범위 중 10% 완료
- 기획서 제출
- 스플래쉬 화면 구현
- 상태바 색상 변경
- BottomNavigationView 구현
- 홈 화면의 툴바 구현

## 2023-03-05
1주차 작업 범위 중 30% 완료
- 홈 화면 툴바 수정
- 홈 화면 구현
  - 홈 화면 스토리 리스트 구현
  - 홈 화면 게시물 리스트 구현
  
이슈
- ViewPager2 적용 안되는 이슈 발생 
  - 에러메세지: Pages must fill the whole ViewPager2 (use match_parent)
  - "match_parent"로 바꿔서 해결

## 2023-03-06
1주차 작업 범위 중 40% 완료
- 로그인 화면 구현

회의록
- 구현 범위 세부사항 논의
  - 부계정 기능은 시간 상 구현하지 않기로 함
  - 게시물에 태그 기능은 우선 순위를 낮게 설정하여 시간이 된다면 구현하기로 함
  - 스토리 기능은 최소 사진은 올릴 수 있게 구현하기로 함
  - 게시물 기능을 우선 순위 높게 설정
  - 릴스나 설정 기능들은 우선 순위 낮게 설정 

1차 피드백
- 개발 속도 높이기
- 인스타그램 스토리 테두리 링은 캡처해서 사용
- 게시물 사진 크기는 ratio 속성과 centerCrop 이용
- 인스타그램 모든 화면을 구현하는 것을 목표로 하기

## 2023-03-07
1주차 작업 범위 중 60% 완료
- 회원가입 화면들 구현
  - 휴대폰 번호 입력 화면
  - 이메일 주소 입력 화면
  - 휴대폰 번호 인증 화면
  - 이메일 인증 화면
  - 이름 입력 화면
  - 비밀번호 입력 화면
  - 사용자 이름 입력 화면
  - 프로필 사진 추가 화면
  - 회원가입 완료 화면
- 모든 원시값 및 문자열 포장

## 2023-03-08
1주차 작업 범위 중 70% 완료
- 프로필 화면 구현
  - 프로필 화면 스토리 리스트 구현
  - 프로필 화면 게시물 리스트 구현

이슈
- TabLayout의 아이콘 크기 변경이 안되는 이슈 발생
  - 아이콘의 크기를 확대해서 캡처해보거나 drawable 파일에서 사이즈를 조절한 후 적용해봤지만 해결X
  - icon 속성을 이용하지 않고 layout 파일을 만든 후 코틀린 파일에서 적용하여 해결 
    - stackoverflow 사이트에서 해결 방법을 찾음

## 2023-03-09
1주차 작업 범위 중 80% 완료
- 스토리 테두리 링 수정
- 댓글 화면 구현
- 생년월일 입력 화면 구현
- 로그인 api 연동

## 2023-03-10
1주차 작업 범위 중 90% 완료
- 로그인 기능 추가 구현
  - 키보드 제어 기능 구현
  - 로그인 오류 다이얼로그 구현
- 회원가입 기능 구현
  - 전화번호 회원가입 api 연동
  - 이메일 회원가입 api 연동
- 프로필 화면 구현
  - 특정 프로필 조회 api 연동

## 2023-03-11
1주차 작업 범위 중 95% 완료
- 프로필 화면 게시물 리스트 구현
  - 게시물이 0개일 때의 화면 구현
  - 사용자 게시물 전체 조회 api 연동
- BottomNavigationView에 프로필 이미지 구현
  - 특정 프로필 조회 api 연동
- 홈 화면 게시물 리스트 구현
  - 팔로잉 전체 게시글 조회 api 연동

이슈
- BottomNavigationView menu 이미지 변경이 안되는 이슈 발생
  - layout 파일에서 그 위에 이미지뷰를 배치하여 구현

## 2023-03-12
1주차 작업 범위 중 100% 완료
- 댓글 화면 구현
  - 댓글 조회 api 연동하여 댓글 화면 구현
  - 댓글 화면 입력창 구현
- 팔로워/팔로잉 화면 구현
  - 팔로워 목록 화면 구현

회의록
- 전체 스토리 조회 api의 Response 데이터 형식에 대해 논의함
- 팔로잉 전체 게시물 조회 api에 스토리 여부 데이터 추가 요청함
- 구현 순서에 대해 논의함

## 2023-03-13
2주차 작업 범위 중 20% 완료
- 팔로워/팔로잉 목록 화면 구현
  - 팔로워 목록 조회 api 연동
  - 팔로잉 목록 조회 api 연동
- 홈 화면 스토리 리스트 구현
  - 스토리 목록 조회 api 연동
- 유저 프로필 화면 구현
  - 특정 프로필 조회 api 연동
  - 사용자 게시물 전체 조회 api 연동
- 프로필 화면 구현 마무리
  - 스토리 하이라이트 목록 구현 마무리
  - 탭 아이콘 선택했을 때 색상 변경 구현

## 2023-03-14
2주차 작업 범위 중 30% 완료
- 게시물 생성 관련 화면들 구현
  - 갤러리 화면 구현
  - 갤러리 이미지 모두 가져오기
  - 선택한 이미지 파일 경로 구하기
- 갤러리 이미지 파일 링크 만들기 시도 중..

2차 피드백
- 인스타그램 스토리 회색 테두리 링 추가
- 수요일까지 게시물 생성, 검색 기능 만들고 목요일에 부가적인 기능 구현과 디테일한 부분 수정하는 작업하는 걸 추천
- api 15개 정도는 연동하기