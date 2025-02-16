# java-lotto
> 로또를 구매하고, 당첨 번호를 입력하면 당첨 여부를 확인하는 로또 시스템

## 시스템 흐름
1. 구입금액을 입력받는다 
2. 구매 개수와 로또 번호들을 오름차순으로 출력한다 
3. 당첨번호를 입력받는다 
4. 보너스 볼을 입력받는다 
5. 당첨 통계와 수익률을 출력한다 
6. 시스템을 종료한다

## 기능 요구사항

### 로또
- [x] 번호가 1 이상 45 이하의 정수인지 검사한다
- [x] 번호에 중복이 존재하는지 검사한다
- [x] 중복되지 않는 1 이상 45 이하의 정수 6개를 로또 번호로 저장한다

### 랜덤 숫자 생성기
- [x] 랜덤으로 정수를 생성한다

### 구매 로또
- [x] 구매 금액이 1000원 이상이고 1000원 단위인지 검사한다
- [x] 구매 금액에 따라 1000원 당 로또 1개를 생성한다

### 당첨 로또
- [x] 보너스 볼이 당첨 번호와 중복되지 않는 1 이상 45 이하의 정수인지 검사한다
- [x] 사용자 구매 로또와 당첨 로또를 통해 구매 로또들의 등수를 계산한다

### 당첨 통계
- [x] 구매 로또들의 등수를 저장한다
- [x] 구매 로또들의 등수를 통해 수익률을 계산한다

### 입력
- [x] 구입금액을 입력받는다
  - [x] 구입금액이 숫자가 아닌 경우 에러 메시지를 출력하고 재입력받는다
  

- [x] 당첨 번호를 입력받는다
   - [x] 당첨 번호가 콤마로 구분한 6개의 요소가 아니라면 에러 메시지를 출력하고 재입력받는다
   - [x] 당첨 번호가 숫자가 아니라면 에러 메시지를 출력하고 재입력받는다
  

- [x] 보너스 번호를 입력받는다
  - [x] 보너스 번호가 숫자가 아니라면 에러 메시지를 출력하고 재입력받는다

### 예외 처리
- [x] 입력이 잘못될 경우 에러 메시지를 출력하고 그 부분부터 재입력받는다