# java-lotto

로또 미션 저장소
- 구입 금액을 입력받는다
    - `로또 가격의 양의 배수`가 아닐 경우 예외 메시지를 출력하고 재입력 받는다.
  

- 구입한 로또 개수 중, **수동으로 구매할 로또 수**를 입력받는다.
    - `0 이상의 정수`가 아닐 경우 예외 메시지를 출력하고 재입력 받는다.
    - `구입 금액을 초과하는 개수`일 경우 예외 메시지를 출력하고 재입력 받는다.
   
 
- **수동으로 구매할 번호**를 입력받는다.
    - `,`로 구매 번호를 구분지은 후, `1 ~ 45의 자연수`가 아닌 값이 존재할 경우, 예외 메시지를 출력하고 재입력 받는다.


- 구입한 로또 개수에서 `수동구매 로또 수를 제외한 개수`만큼 로또 번호 목록들을 **랜덤으로 생성**해 출력한다.
    - 로또 번호의 범위는 `1 ~ 45 자연수`이다.
    - 로또 번호들은 `오름차순`으로 정렬한다.
    - 로또 번호 생성시, 같은 줄에 **중복된 번호가 없도록** 생성한다.
    - **다른 줄과 모두 동일한 번호**들을 가진 줄이 없도록 생성한다.
  

- 지난 주 당첨 번호를 입력받는다.
    - `1 ~ 45의 자연수`가 아닌 값이 존재할 경우, 예외 메시지를 출력하고 재입력 받는다.
    - 입력 받은 숫자가 `6개`가 아닌 경우, 예외 메시지를 출력하고 재입력 받는다.
    - **입력 받은 숫자들 간에 중복이 있는 경우**, 예외 메시지를 출력하고 재입력 받는다. 
  

- 보너스 볼을 입력 받는다.
    - `1 ~ 45 자연수`가 아닌 경우, 예외 메시지를 출력하고 재입력 받는다.
    - **지난 주 당첨 번호에 있는 값**이 입력될 경우, 예외 메시지를 출력하고 재입력 받는다.
  

- 당첨 통계를 출력한다.
  - 일치하는 개수에 따른 당첨 금액과 줄의 수를 출력한다.
  - 총 수익률 소수점 2자리까지 출력한다.

    
