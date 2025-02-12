# java-lotto

로또 미션 저장소

기본) 기능 요구사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

추가) 기능 요구사항
- 로또는 6개의 당첨 번호와 보너스 번호가 1개 있다.
- 당첨 번호와 보너스 번호는 겹치면 안된다.
- 로또 번호는 오름차순으로 정렬된다.
- 로또 번호는 1 ~ 45 다.
- 당첨 통계를 알려준다 (수익률(기준:1) 포함)

추가추가) 기능 요구사항
- 로또는 1 ~ 5등까지 있다.

**TODO: 예외**


`실행 결과`
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

___

구현해야하는 기능
1. `구입금액을 입력해 주세요.` 출력
2. 구입금액 입력
3. `N개를 구매했습니다` 출력
4. N개의 로또 목록 출력
5. `지난 주 당첨 번호를 입력해 주세요.` 출력
6. 당첨 번호 입력 (`, `로 구분)
7. `보너스 볼을 입력해 주세요.` 출력
8. 보너스 볼 번호 입력
9. 당첨 통계 출력 (수익률 포함)



