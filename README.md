# java-lotto

## 로또 미션 저장소

### 로또 구매

- [x] 구입금액 안내멘트를 출력한다.
- [x] 구입금액을 입력한다.
    - 로또 1장의 가격은 1000원
- [x] 구매 결과를 출력한다.

### 로또 발행

- [x] 6개의 랜덤한 숫자를 구입 개수만큼 생성한다.
    - 숫자의 범위는 1~45
- [x] 생성된 번호를 화면에 출력한다.
    ```
    [8, 21, 23, 41, 42, 43]
    [3, 5, 11, 16, 32, 38]
    [7, 11, 16, 35, 36, 44]
    ```

### 번호 입력

- [x] 번호 입력 안내멘트를 출력한다.
- 쉼표로 구분된 6개의 숫자를 입력받는다.
    - 숫자의 범위는 1~45
- 보너스 번호 입력 안내멘트를 출력한다.
- 보너스 번호를 입력받는다.
    - 숫자의 범위는 1~45

### 당첨 통계

- 각 로또별로 당첨번호와 일치하는 숫자 개수를 카운팅한다.
    - 1등: 6개 일치
    - 2등: 5개 일치, 보너스 볼 일치
    - 3등: 5개 일치
    - 4등: 4개 일치
    - 5등: 3개 일치
- 당첨 결과를 출력한다.
  ```
  3개 일치 (5000원)- 1개
  4개 일치 (50000원)- 0개
  5개 일치 (1500000원)- 0개
  5개 일치, 보너스 볼 일치(30000000원) - 0개
  6개 일치 (2000000000원)- 0개
  ```

### 수익률

- 총 당첨금을 계산한다.
    - 1등: 2,000,000,000원
    - 2등: 30,000,000원
    - 3등: 1,500,000원
    - 4등: 50,000원
    - 5등: 5,000원
- 계산된 수익률을 출력한다

### 예외처리

구입금액 입력

- [x] 양의 정수가 아닌 입력은 예외 처리한다.
- [x] 1,000원으로 나누어떨어지지 않는 입력은 예외 처리한다.

당첨 번호 입력

- [x] 양의 정수가 아닌 입력은 예외 처리한다.
- [x] 1~45 범위가 아닌 입력은 예외 처리한다.
- [x] 쉼표로 나눴을 때 6개가 아닌 경우 예외 처리한다.
- 중복된 번호를 입력할 경우 예외 처리한다.

보너스 번호 입력

- 양의 정수가 아닌 입력은 예외 처리한다.
- 1~45 범위가 아닌 입력은 예외 처리한다.
- 당첨 번호와 중복된 번호를 입력할 경우 예외 처리한다.
