## 기능 명세서

### 로또 구입

- 로또 구입 금액을 입력한다.

### 로또 발급

- 구입 금액에 해당하는 로또 개수를 출력한다.
- 구입한 로또 개수만큼 로또를 발급한다.

### 당첨 통계 계산

- 지난 주 당첨 번호와 보너스 볼을 입력한다.
- 로또 당첨 통계를 구한다.
    - 로또 당첨 여부를 계산한다.
    - 당첨 등수별 개수를 출력한다.
- 총 수익률을 계산한다.
    - 수익률은 둘째 자리까지 반올림해서 출력한다.

### 예외

- 로또 구입 금액
    - 1000원 미만인 경우
    - 정수가 아닌 경우
    - 숫자가 아닌 경우
    - Integer 범위가 아닌 경우
- 로또 번호
    - 1~45 범위를 벗어나는 번호인 경우
- 로또
    - 중복되는 로또 번호가 존재하는 경우
    - 로또 번호가 6개가 아닌 경우
