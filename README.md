# java-lotto

## 💵 구입 금액

### 기능
- [ ] 안내문구를 출력하고 구입금액을 입력받는다.
- [ ] 로또 구입금액 단위는 1000원이다.
- [ ] 구입한 로또 티켓 개수를 출력한다.

### 예외
- [ ] 구입금액이 1000원 단위가 아니라면 예외를 발생시킨다.
- [ ] 구입금액이 숫자가 아니라면 예외를 발생시킨다.
- [ ] 구입금액이 0과 같거나 작으면 예외를 발생시킨다.

## 🔢 로또 티켓

### 기능
- [ ] 로또 번호는 1부터 45 사이이다.
- [ ] 로또 번호의 개수는 6개이다.
- [ ] 로또 번호는 중복되지 않는다.
- [ ] 로또 번호는 오름차순으로 출력된다.
- [ ] 로또 번호는 다음과 같은 형식을 지킨다.
  ``` [8, 21, 23, 41, 42, 43]```

## 🥇 당첨 번호

### 기능
- [ ] 안내문구를 출력하고 당첨 번호를 입력받는다.
  - [ ] 당첨번호를 쉼표+빈칸(, )으로 구분한다.
- [ ] 당첨 번호는 1부터 45 사이이다.
- [ ] 당첨 번호는 6개이다.
- [ ] 당첨 번호는 중복되어서는 안된다.

### 예외
- [ ] 쉼표+빈칸(, )로 구분한 당첨 번호가 숫자가 아니라면 예외 발생시킨다.
- [ ] 당첨 번호는 1부터 45 사이가 아니라면 예외를 발생시킨다.
- [ ] 당첨 번호가 6개가 아니라면 예외를 발생시킨다.
- [ ] 당첨 번호는 중복되면 예외를 발생시킨다.

## 🎱 보너스 볼

### 기능
- [ ] 안내문구를 출력하고 보너스 볼을 입력받는다.
- [ ] 보너스 볼은 1부터 45 사이이다.
- [ ] 보너스 볼은 1개이다.
- [ ] 보너스 볼은 당첨 번호와 중복되어서는 안된다.

### 예외
- [ ] 보너스 볼이 숫자가 아니라면 예외 발생시킨다.
- [ ] 보너스 볼이 1부터 45 사이가 아니라면 예외를 발생시킨다.
- [ ] 보너스 볼이 1개가 아니라면 예외를 발생시킨다.
- [ ] 보너스 볼이 당첨번호와 중복되면 예외를 발생시킨다.

## 🎬 당첨 결과

### 기능
- [ ] 당첨번호와 구입한 로또 번호를 비교하여 일치한 번호의 개수를 계산한다.
  - [ ] 5개가 일치하는 경우, 보너스 볼 번호와 구입 로또 번호와 일치하는 지 확인한다.
- [ ] 구입금액을 바탕으로 수익률을 계산한다.
  - [ ] 소숫점 2자리까지 출력한다.
- [ ] 다음과 같이 당첨 결과를 출력한다.
    ```
    3개 일치 (5000원)- 1개
    4개 일치 (50000원)- 0개
    5개 일치 (1500000원)- 0개
    5개 일치, 보너스 볼 일치(30000000원) - 0개
    6개 일치 (2000000000원)- 0개
    ```
- [ ] 다음과 같이 수익률을 출력한다.
    ```
    총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
    ```

**의문점**
- 요구사항을 정의할때, 기능과 예외가 중복되어도 되는가? 혹은 규칙으로 분류해야 하는가?
