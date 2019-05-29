# java-lotto
로또 미션 진행을 위한 저장소



## 기능 구현 목록

- LottoNumber : 1~45까지의 범위를 갖는, int lottoNumber를 포장한 클래스

  - 1~45 범위를 벗어나는 수를 받을 경우 예외처리

- lotto : 6개의 LottoNumber를 가지고 있는 Lotto클래스

  - List<LottoNumber>의 일급컬렉션으로 만듦
  - ManualLottoGenerator와 AutoLottoGenerator로부터 List<LottoNumber>를 생성 받아 객체 생성

- Money : 로또 구입 금액을 의미하는 클래스

  - 현실의 법을 반영해 1000원 이상, 100,000원 이하의 int값을 가짐.

- WinLotto : 당첨 번호를 가지고 있는 클래스

- BonusNumber : 보너스 넘버 클래스

  - WinLotto와 새로 생성한 LottoNumber를 받아 비교해서 보너스 넘버가 될 LottoNumber가 WinLotto에 포함되어 있는지 확인하는 isDuplicated() 메서드를 가지고 있음

- Rank : 당첨 순위에 따른 상금을 가지고 있는 enum.

  - 금주의 당첨 번호와 일치하는 번호의 숫자와 보너스 번호가 일치하는 지를 입력 받아 상금을 리턴한다.

- ManualLottoGenerator : 사용자로부터 입력 받은 문자열을 토대로 List<LottoNumber>를 만들어 리턴하는 클래스

- AutoLottoGenerator : 무작위로 만든 List<LottoNumber> 리스트를 리턴하는 클래스

  

