# java-lotto

## 도메인 설명

- 로또 번호는 1 이상 45 이하이다.
- 로또는 6개의 숫자 묶음이다.
- 로또 1개의 가격은 1000원이다.

## 요구사항

### 구입금액 입력

- 입력 검증
    - [x] 양수여야 한다.
    - [x] 로또 1개 금액(1000)으로 나누어 떨어져야 한다.

### 구입한 로또 번호 출력

- [x] 입력한 구입금액에 맞는 로또 개수를 구한다.
- [x] 구입한 로또 개수만큼 로또를 발행한다.
- [x] 발행한 로또 번호들을 출력한다.

### 당첨 번호 입력

- 입력 검증
    - [x] 6개의 로또 번호를 입력받는다.
    - [x] 각 로또 번호는 `,`로 구분한다.
    - [x] 번호는 중복되면 안 된다.

### 보너스 번호 입력

- 입력 검증
    - [x] 1개의 로또 번호를 입력받는다.
    - [x] 위에서 입력받은 당첨 번호와 중복되면 안 된다.

### 당첨 통계 출력

- [x] 당첨 통계를 계산 후 출력한다.
- [x] 총 수익률을 계산 후 출력한다.
    - [x] 수익률은 소수점 셋째 자리에서 내림해서 출력한다.

## 예외 처리

- 입력 형식이 맞지 않는 경우, 예외 메세지 출력 후 예외가 발생한 지점부터 재입력을 받는다.
