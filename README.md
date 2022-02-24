# java-lotto
## 기능 요구 사항
- [x] 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- [x] 로또 1장의 가격은 1000원이다.
  - [x] 1000원 단위의 숫자가 아닌 경우 예외를 던져준다.

### 로또
- [x] 6개의 랜덤한 양수(1~45)를 가진다
  - [x] 중복을 허용하지 않는다.
  - [x] 로또 1장의 가격은 기본 1000원이다.
- [x] 로또의 번호는 정렬된 상태여야한다. 
   
### 로또 목록
- [x] 로또 생성
- [x] 구입금액을 받아서 해당하는만큼 로또 생성
- [x] 생성된 로또를 모아 반환

### 지난 주 당첨 번호
- [x] 중복 번호가 존재하는 경우 예외를 던져준다.
- [x] 로또 번호 범위의 양수(1~45)가 아닌 경우 예외를 던져준다.
- [x] 6개보다 적거나 많은 경우.
- 
### 로또 결과
- [x] 당첨 번호와 보너스 볼와 생성된 로또들을 비교
- [x] 몇개의 번호가 일치하는지 판정
- [x] 각 등수에 해당하는 로또 개수 판정
- [x] 수익률 계산

### 보너스
- [x] 지난주 당첨 번호와 중복된 번호인 경우 예외를 던져준다.
- [x] 로또 번호 범위의 양수(1~45)가 아닌 경우 예외를 던져준다.

### 사용자 입력
- [x] 구입 금액을 입력받기
  - [x] 숫자가 아닌 문자를 입력한 경우 예외를 던져준다.
- [x] 지난주 당첨 번호 입력받기
  - [x] 지정된 형식의 입력이 아닌 경우 예외를 던져준다.
    - [x] 숫자가 아닌 입력
- [x] 보너스볼을 입력
  - [x] 숫자가 아닌 문자를 입력한 경우 예외를 던져준다.

### 출력
- [x] 생성된 로또 번호들을 출력한다.
- [x] 구매한 로또의 갯수를 출력한다.
- [x] 당첨 통계를 출력한다.
- [x] 수익률을 출력한다.


### 기타
#### 추가 요구사항 
- [x] 배열 대신 컬렉션을 사용한다.
- [x] Java Enum을 적용한다.
- [x] 모든 원시 값과 문자열을 포장한다
- [x] 줄여 쓰지 않는다(축약 금지).
- [x] 일급 컬렉션을 쓴다.

#### 힌트
- 로또 자동 생성은 Collections.shuffle()를 활용한다.
- Collections.sort()를 활용해 정렬 가능하다.
- ArrayList의 contains()를 활용하면 어떤 값이 존재하는지 유무를 판단할 수 있다.

