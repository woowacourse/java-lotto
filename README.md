# java-lotto 레벨-2

implement : 투다

# 고려한 점

## static의 무분별한 사용

### static의 장점

static은 여러번 사용되는 필드 등에서는 초기 한번만 메모리에 상주하기 때문에 메모리를 아낄 수 있는 부분이 존재함.

### 현재 무분별한 사용

그러나 현재 LottoRandomGenerator의 경우, 현재 모든 필드가 static으로 선언되어 있음    
LottoRandomGenerator는 특정시점에 한번만 사용되는데 불구하고 static으로 선언되어 있기 때문에 애플리케이션이 실행되자마자 메모리를 점유하게 된다.    
따라서 필요 시점에 한번만 생성하여 사용하도록 하였음

## formatter

- formatter 또한 굳이 패키지를 formatter란 이유만으로 선언할 필요가 없음

## Repository의 필요성

- 기존에 유저와 컨트롤러간 입출력이 많이 발생하는 경우에 데이터들을 관리하기가 번거롭다는 이유로 사용하였음.
- 하지만 해당 미션에서는 로또를 영속성으로 관리할 필요가 없고, 단순 저장을 위해서라면 굳이 Repository라는 명목으로 사용할 필요가 없다
  **(불필요한 생명주기)**

## 제약조건을 프리하게 두는것

- 사용자가 적합하지 않은 입력을 했을때 이를 허용시키는 것은
  오히려 "어디까지 허용해줘야하지?"하는 혼돈을 초래할 수 있다

# 기능 명세

- 예외 발생 시 예외 발생 시점부터 재입력 받는다.

---

## 로또 구매 기능

- [] 로또 구매 금액을 입력받는다.
- [] 0원이 입력되면 예외가 발생한다.
- [] 숫자가 아닌 문자열이 입력되면 예외가 발생한다.
- [] 1,000으로 나누어 떨어지지 않은 금액 입력 시 예외가 발생한다.
- [] 각 로또 번호 6개는 서로 중복되지 않는다.
- [] 로또 번호는 6개여야한다.
- [] 사용자가 구매한 수량만큼의 랜덤 생성한다.
- [] 사용자가 구매한 로또 내역을 출력한다.

## 당첨번호 입력

- [] 사용자에게 당첨 번호를 입력 받는다.
- [] 당첨번호는 ,로 구분한다.

- [] 당첨번호가 숫자가 아니면 예외가 발생한다.
- [] 당첨번호 6개는 서로 중복되지 않는다.
- [] 당첨번호가 1~45내의 숫자가 아니면 예외가 발생한다.
- [] 입력된 당첨번호가 6개가 아닐 시 예외가 발생한다.

## 보너스볼 입력

- [] 사용자에게 보너스 번호를 입력 받는다.

- [] 보너스 번호가 숫자가 아니면 예외가 발생한다.
- [] 보너스 번호가 1~45내의 숫자가 아니면 예외가 발생한다.

## 당첨 통계 계산 및 출력

- [] 구매한 로또와 당첨번호를 비교하여 로또 일치 결과를 계산한다.
- [] 당첨 통계 및 수익률을 출력한다.
