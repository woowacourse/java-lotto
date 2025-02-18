# java-lotto
# 로또 미션 저장소
## 기능 요구사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

## 구현 기능 목록
- [x] 구입 금액 입력받기
- [x] 당첨 번호 입력받기
- [x] 보너스볼 입력받기
- [x] 잔돈 출력하기
- [x] 발행된 로또를 출력하기
- [x] 로또 당첨 결과 출력하기
- [x] 금액 세 자리수 구분 포맷하기
- [x] 수익률 세 자리수 구분과 소수점 아래 채워 포맷하기

- [x] 금액이 부족한지 알려주기
- [x] 로또 개수 계산하기
- [x] 잔돈 계산하기
- [x] 숫자 6개 자동생성하기 
- [x] 로또 개수에 맞게 자동 발급하기
- [x] 당첨 로또 생성하기
- [x] 로또 등수 계산하기
- [x] 로또 당첨 결과 계산하기
- [x] 로또 당첨 수익률 계산하기

## 예외처리
- [x] 구매 금액 예외처리
  - 구매 금액 입력을 숫자로 변환할 수 없으면 `IllegalArgumentException`을 발생시킨다. 
  - 구매 금액이 0원 이하이면 `IllegalArgumentException`을 발생시킨다. 
  - 구매 금액이 구매 최소 금액 미만이면 `IllegalArgumentException`을 발생시킨다. 
- [x] 로또 번호 예외처리
  - 개수가 6개가 아닌 경우 `IllegalArgumentException`을 발생시킨다.
  - 범위가 1이상 45이하가 아닌 경우 `IllegalArgumentException`을 발생시킨다.
  - 중복된 숫자가 존재하는 경우 `IllegalArgumentException`을 발생시킨다.
- [x] 당첨 로또 예외처리
  - 당첨 로또 번호 개수가 6개가 아닌 경우 `IllegalArgumentException`을 발생시킨다.
  - 당첨 로또 번호에 중복된 숫자가 존재하는 경우 `IllegalArgumentException`을 발생시킨다.
  - 로또 번호와 보너스번호에 중복이 존재하는 경우 `IllegalArgumentException`을 발생시킨다.
  - 범위가 1이상 45이하가 아닌 경우 `IllegalArgumentException`을 발생시킨다.
- [x] 로또 번호 자동 생성기 예외처리
  - 최소값이 최대값보다 큰 경우 `IllegalArgumentException`을 발생시킨다.
  - 번호 개수가 0보다 작은 경우 `IllegalArgumentException`을 발생시킨다.
  - 번호 범위가 개수보다 작으면 `IllegalArgumentException`을 발생시킨다.
