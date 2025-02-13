# 로또

기능 요구사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

입력
- [x] 구입 금액을 입력 받는다.
  - 예외
    - [ ] 1000원 단위가 아닐 경우 예외를 발생시킨다.
    - [ ] 양수가 아니거나 문자이거나 공백일 경우 예외를 발생시킨다.
- [x] 당첨 번호를 입력 받는다.
  - 예외
    - [x] 숫자가 6개가 아닐 경우 예외를 발생시킨다.
    - [x] 중복되는 숫자를 입력할 경우 예외를 발생시킨다. 
    - [x] 1~45 사이 숫자가 아닐 경우 예외를 발생시킨다.
    - [ ] 쉼표로 구분하지 않을 경우 예외를 발생시킨다.
- [x] 보너스 번호를 입력 받는다.
  - 예외
    - [x] 당첨번호와 중복되는 숫자를 입력할 경우 예외를 발생시킨다.
    - [x] 1~45 사이 숫자가 아닐 경우 예외를 발생시킨다.

구매
- [x] 구입금액을 토대로 로또 티켓 장 수를 결정한다.

당첨 번호 생성
- [x] 입력값을 쉼표 기준으로 나누어 당첨 번호를 생성한다.


로또 번호 생성
- [x] 구매 수량만큼 1~45 사이의 숫자 6개를 랜덤으로 생성한다.
  - 예외
    - [x] 중복되는 숫자가 있다면 다시 생성한다.

결과 집계
- [ ] 당첨 번호와 로또 번호를 비교한다.
  - [ ] 1등: 6개가 일치하는 경우
  - [ ] 2등: 5개가 일치하고 보너스 번호가 일치하는 경우
  - [ ] 3등: 5개가 일치하는 경우
  - [ ] 4등: 4개가 일치하는 경우
  - [ ] 5등: 3개가 일치하는 경우 

 수익률 계산
 - [ ] 결과 집계를 토대로 수익률을 계산한다.

출력
- [x] 구매 로또 티켓 장 수를 출력한다.
- [x] 생성된 로또 번호를 출력한다.
- [ ] 당첨 통계를 출력한다.
- [ ] 수익률 게산 결과를 출력한다.