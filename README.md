# java-lotto
로또 미션 진행을 위한 저장소
---
###기능 요구 사항
- 구입 금액 입력
- 로또 수 입력
- 수동으로 구매할 번호 입력
    - 6개 숫자
    - ','로 구분
    -return으로 다음 수동 입력
- 번호 출력
- 지난 주 당첨 번호 입력
- 보너스 볼 입력
- 당첨 통계 출력
- 총 수익률 계산

###to do list
- [ ] 로또 번호 객체
    - [x] 1 이상 45이하
- [x] 로또 번호 모음 객체
    - [x] 6개 번호여야함
    - [x] 중복 없어야함
- [x] 로또 객체
    - [x] 하나의 로또 번호 모음
    - [x] 수동 생성
    - [x] 자동 생성(랜덤)
- [ ] 자판기 입력
    - [x] 금액 입력
        - [x] 1000 이하면 다시
        - [x] 거스름돈 출력
    - [ ] 수동 갯수 입력
        - [ ] 구매 숫자보다 적게
    - [ ] 수동 번호 입력
        - [ ] ,로 구분 
        - [ ] 6개 숫자
- [ ] 자판기 로직
    