# java-lotto

로또 미션 저장소

# 구현 기능 목록

### 1. 구입 금액을 입력받는다. (완료)

- 구입 금액은 1,000 원 단위여야 한다.
- 구입 금액이 숫자가 아니면 IllegalArgumentException 을 발생시킨 후, 애플리케이션을 종료한다.
- int 오버플로우 발생 시 IllegalArgumentException 을 발생시킨 후, 애플리케이션을 종료한다.

### 2. 구입 금액에 따라 로또를 발행한다. (완료)

- 구입 금액이 1000 단위가 아니면 IllegalArgumentException 을 발생시킨 후, 애플리케이션을 종료한다.
- 구입 금액이 음수이면 IllegalArgumentException 을 발생시킨 후, 애플리케이션을 종료한다.
- 구입 금액에 따라 로또 갯수를 계산한다.
- 로또 갯수 만큼 6개의 랜덤 번호들을 생성한다.
- 발행 된 번호들이 6개가 아니면 IllegalArgumentException 을 발생시킨 후, 애플리케이션을 종료한다.
- 발행 된 번호들은 1~45 범위 사이여야 한다.
- 발행 된 번호들은 중복 되지 않아야 한다.

### 3. 발행받은 로또들을 출력한다. (완료)

- 발행받은 로또의 수를 출력한다.
- 발행받은 모든 로또를 출력한다. 이때, 오름차순으로 정렬하여 출력해야 한다.

### 4. 지난 주 당첨 번호를 입력받는다. (완료)

- 입력하는 양식이 다르면 IllegalArgumentException 을 발생시킨 후, 애플리케이션을 종료한다.
    - 구분자를 `', '` 로 하지 않은 경우
    - 구분한 문자가 정수형이 아닌 경우
- split 으로 구분한 정수를 List 로 반환한다.
    - 구분자는 `', '` 이다.

### 5. 보너스 번호를 입력받는다. (완료)

- 입력받는 문자가 정수형이 아니면 IllegalArgumentException 을 발생시킨 후, 애플리케이션을 종료한다.

### 6. 지난 주 당첨 번호와 보너스 번호를 통해 로또 결과를 계산한다. (완료)

- 당첨 로또 번호가 보너스 번호를 포함한다면 IllegalArgumentException 을 발생시킨 후, 애플리케이션을 종료한다.
- 당첨 로또 번호에 중복이 존재한다면 IllegalArgumentException 을 발생시킨 후, 애플리케이션을 종료한다.
- 가능한 로또 결과는 다음과 같다.
    - 3개 일치 (5,000 원)
    - 4개 일치 (50,000 원)
    - 5개 일치 (1,500,000 원)
    - 5개 일치 + 보너스 볼 일치 (30,000,000 원)
    - 6개 일치 (2,000,000,000 원)
- 처음에 발행한 로또들과 당첨 로또 간의 결과를 계산해야 한다.
- 주의할 점: 1등 로또가 많을 경우, overflow 가 일어날 수 있으므로, 유의해야 한다.
    - 해당 사항을 구현할 시간이 없는 경우, TODO 로 남긴다.
        - TODO 로 남기기로 결정.

### 7. 계산한 로또 결과를 출력한다. (완료)

- 각 결과를 다음과 같은 형식으로 출력한다.
    - 보너스 볼이 일치하지 않는 경우.
        - `{}개 일치 ({}원)- {}개`
    - 보너스 볼이 일치하는 경우.
        - `{}개 일치, 보너스 볼 일치({}원) - {}개`
- 모든 결과를 합해 나온 당첨금과 구입 금액을 이용해 수익률을 계산하여 출력한다.
    - 수익률은 소숫점 둘째 자리까지만 출력한다(반올림해야 한다).

### TODO: 예외 처리

