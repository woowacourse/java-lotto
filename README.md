# java-lotto
로또 미션 진행을 위한 저장소



## 기능 구현 목록

- Number : 1~45까지의 범위를 갖는 int값을 포장한 클래스

  - 1~45 범위를 벗어나는 수를 받을 경우 예외처리



- lotto : 6개의 서로 다른 Number를 가지고 있는 Lotto클래스

  - List<Number>의 일급컬렉션으로 만듦
  - inputView에서 List<Number>를 넘겨 받아 LottoGenerator로 Lotto 인스턴스를 생성함



- Money : 로또 구입 금액을 의미하는 클래스

  - 현실의 법을 반영해 1000원 이상, 100,000원 이하의 int값을 가짐.
  - availablePurchaseTicketCount() : 입력받은 돈을 로또 가격인 1000원으로 나누어 구입 가능한 로또 개수를 리턴함.



- WinningLotto : 이번주의 당첨 로또와 보너스 넘버를 가지고 있는 클래스
  - matchCount() : 구입한 로또와 당첨 로또를 비교해 일치하는 로또 번호의 갯수를 리턴한다
  - matchBonusNumber() : 구입한 로또와 보너스 넘버를 비교해 일치하는지의 여부를 boolean으로 리턴한다.



- Rank : 당첨 순위에 따른 상금을 가지고 있는 enum.

  - 금주의 당첨 번호와 일치하는 번호의 숫자와 보너스 번호가 일치하는 지를 입력 받아 상금을 리턴한다.



- ManualLottoGenerator : 사용자로부터 입력 받은 LIst<Number>를 토대로 Lotto를 만들어 리턴하는 클래스



- AutoLottoGenerator : 무작위로 만든 Lotto를 리턴하는 클래스

  
  
- InputView : Console 입력을 담당함

  - 로또 구입 금액 입력
  - 수동 구매 매수 입력
  - 수동 번호 입력
    - 수동 구입 로또 번호
    - 이번주 당첨 번호 
  - 보너스 볼 입력



- OutputView : console 출력을 담당함

  

