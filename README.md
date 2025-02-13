# java-lotto

로또 미션 저장소


## 기능 요구사항
- [ ] 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- [ ] 로또 1장의 가격은 1000원이다.
- [x] 구입 금액 입력 기능
- [x] 로또 구매 기능
    - [x] 로또 번호는 1-45까지
    - [x] 로또의 번호 개수는 6개
    - [x] 구입 금액에 따라 구매할 로또 개수 결정
    - [x] 발급할 로또 개수만큼 로또 번호 발급
    - [x] 구매 로또 번호는 중복되지 않음
    - [x] 구매한 로또 번호 출력 기능
    - [ ] TODO 발급한 로또 번호는 오름차 순으로 저장한다

- [x] 지난 주 당첨 번호 입력 기능
    - [x] 콤마로 구분하여 입력한다
    - [x] 당첨 번호는 6개
    - [x] 당첨 번호는 중복되지 않는다
    - [ ] TODO 콤마, 공백, 숫자 이외 입력시 예외
- [x] 보너스볼 입력 기능

- [ ] 당첨 통계 출력 기능
    - [x] 일치하는 당첨 번호 및 보너스 번호 개수 카운트 기능
    - [x] 등수별 상금 값을 구할 수 있는 기능
    - [x] 번호 매칭 개수 따라서 순위를 계산하는 기능
    - [x] 순위별 당첨 횟수를 계산하는 기능
    - [x] 로또 당첨 개수 목록 출력 
    - [x] 수익률 계산 기능
    - [ ] 수익률 출력

## 예외 사항
- 로또 번호는 1-45까지
- 구매 로또 번호는 중복되지 않음
- 당첨 번호와 보너스 번호는 중복되지 않음
- TODO: 1000원 단위로 나뉘어지지 않는 경우?
...


## 실행 결과
```
구입금액을 입력해 주세요.
14000
14개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6
보너스 볼을 입력해 주세요.
7

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
5개 일치, 보너스 볼 일치(30000000원) - 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
```