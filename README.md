## 기능 구현 목록

### 구입금액 입력

- 구입 금액을 입력한다.

ex) 14500 이면 14000원 어치만 구매

- [x]  [예외] 구입 금액이 1000원 미만일때
- [x]  [예외] 입력이 숫자가 아닐때
- [x]  [예외] 입력이 공백일때
- [x]  [예외] 입력이 Null일때

### 구매 갯수
- [x] ~~입력된 금액을 1000원으로 나누어 구매 갯수를 정한다.~~
- [x] 수동으로 구매할 로또의 갯수를 입력받는다.
- [x]  [예외] 구입금액 안에서 살 수 없는 경우
 
### 로또 구매
- [x] 수동으로 구매할 로또 번호를 입력받은 뒤 로또를 생성한다.
- [x] 구매할 수 있는 갯수만큼 로또를 생성한다.
• 로또 자동 생성은 **`Collections.shuffle()`**를 활용한다.

### 로또 정보 출력
• **`Collections.sort()`**를 활용해 정렬 가능하다. 
- [x] 뷰는 Dto로 데이터를 전달받아 출력한다.

### 당첨 번호 입력

- 당첨 번호를 입력한다.
- [x]  [예외] 입력이 Null일때 
- [x]  [예외] 입력이 공백일때
- [x]  [예외] 입력이 6개가 아닐때
- [x]  [예외] 입력이 숫자가 아닐때
- [x]  [예외] 1~45 사이의 숫자가 아닐 때

### 보너스볼 입력

- 보너스볼을 입력한다.
- [] 보너스볼과 당첨번호들이 중복되지 않게 해야한다.
- [x]  [예외] 입력이 Null일때
- [x]  [예외] 입력이 공백일때
- [x]  [예외] 입력이 1개 이상일때
- [x]  [예외] 입력이 숫자가 아닐때
- [x]  [예외] 1~45 사이의 숫자가 아닐때

### 당첨 판정

• **`ArrayList`**의 **`contains()`**를 활용하면 어떤 값이 존재하는지 유무를 판단할 수 있다.

- 로또 한개씩 돌면서 contains 실행 → 일치 갯수로 등수까지 판정

### 당첨 통계 출력

- 당첨 결과를 출력한다.
- Result 에 Map<Enum, 갯수>

- 구매 로또
    - List<로또 넘버>
- 결과
    - Map<Enum, 갯수>
    - 수익률
- 사용자
    - 돈..ㅋㅋ
    - 소유하고 있는 로또들..?(일급컬렉션 하나로 할수도)
- 당첨번호
    - List<로또 넘버>
- 보너스볼
    - 로또 넘버