
# java-lotto
로또 미션 진행을 위한 저장소

## 기능구현목록 

1. 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
2. 로또 1장의 가격은 1000원이다.

* 로또 구입 금액을 입력받는 기능
* 구입 금액 원시값을 Money 객체로 포장한다.
* 구입 금액은 0이상이어야 한다.
* 구입금액에 맞게 로또를 발급하는 기능
* 구입금액에 따라 로또 개수를 반환하는 기능
* N개의 로또를 생성하는 기능
* 지난주 당첨 번호와 보너스 볼을 입력받는 기능 (번호는 쉼표로 구분한다.)
* 지난주 당첨 번호의 당첨 번호와 보너스볼이 중복되면 안된다.
* 보너스볼, 로또 번호 원시값을 LottoNumber 객체로 포장한다.
* 로또 번호, 보너스볼이 1이상 45이하의 숫자이다.
* 로또 번호들을 LottoNumbers 일급 컬렉션으로 포장한다.
* 당첨번호와 일치하는 숫자의 개수를 파악하는 기능
* 보너스볼과 일치하는 숫자가 있는지 파악하는 기능
* 로또 번호는 중복될 수 없다.
* 로또 번호의 개수는 6자리이다.
* 당첨 로또 등수를 반환하는 기능
* 총 수익률을 계산하는 기능 (수익률 = 총 당첨금액 / 총 구매금액 * 100)

## to-do

* Money 잔돈을 어떻게 처리할지? 잔돈 계산과 로또 구매 개수 계산의 책임에대한 고민
* BonusBall과 LottoNumber의 추상 클래스 상속 vs 인터페이스 구현
  * 추상 클래스를 상속하면 상수를 공유할 수 있어,

## 리팩토링 예정 대상

* Money::divide - 메서드명
* BonusBall의 예외 처리 -> Wrapper 클래스 정의하기

