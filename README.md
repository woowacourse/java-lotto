# java-lotto
로또 미션 진행을 위한 저장소

## 기능 요구사항

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.


## 프로그래밍 요구사항

- indent(인덴트, 들여쓰기) depth를 2단계에서 1단계로 줄여라.
    - depth의 경우 if문을 사용하는 경우 1단계의 depth가 증가한다. if문 안에 while문을 사용한다면 depth가 2단계가 된다.
- else를 사용하지 마라.
- 메소드의 크기가 최대 10라인을 넘지 않도록 구현한다.
    - method가 한 가지 일만 하도록 최대한 작게 만들어라.
- **배열 대신 ArrayList를 사용한다.**
- java enum을 적용해 프로그래밍을 구현한다.
- **규칙 3: 모든 원시값과 문자열을 포장한다.**
- **규칙 5: 줄여쓰지 않는다(축약 금지).**
- **규칙 8: 일급 콜렉션을 쓴다.**

---

## 클래스 구조

## Model

- **PurchaseAmount**
    - int amount
    - [예외처리] 구매 금액
        - checkNotNumber 숫자인지 아닌지 검증
        - checkNegativeNumber 음수인지 검증
        - checkUnderLottoPrice 로또 한 장 가격보다 낮은 금액인지 검증
- **Lotto**
    - List<LottoNumber> lotto
    - [예외 처리] checkLottoSizeSix 6개의 숫자가 만들어졌는 지 검증
    - [예외 처리] checkDuplicatedNumber 중복된 숫자가 있는 로또인지 검증
- **LottoResult**
    - FIRST(6, 0, 2000000000, "6개 일치(2000000000원) - ")
    - SECOND(5, 1, 30000000, "5개 일치, 보너스볼 일치(30000000원) - ")
    - THIRD(5, 0, 1500000, "5개 일치(1500000원) - ")
    - FOURTH(4, 0, 50000, "4개 일치(50000원) - ")
    - FIFTH(3, 0, 5000, "3개 일치(5000원) - ")
- **LottoNumber**
    - int number
    - [예외 처리] checkLottoNumber 1부터 45까지 숫자인지 검증
- **WinningNumber**
    - List<LottoNumber> winningNumber
    - int bonusNumber
    - [예외 처리] checkLottoNull  lotto가 null인지 검증
    - [예외 처리] checkNotNumber  입력된 번호 중 숫자가 아닌 문자가 들어왔을 경우 검증
    - [예외 처리] checkWinningNumberSize  당첨 번호의 개수 검증
    - [예외 처리] checkDuplicatedNumber 중복된 번호가 있는지 검증
- **LottoFactory**
    - [예외 처리] 난수가 1~45까지 수 인지 검증
- **RandomNumberGenerator**

## Controller

- **LottoGame**
    - List<Lotto> lottoDummy

## View

- InputView
    - inputPurchaseAmount
    - inputWinningNumbers
    - inputBonusNumber
- OutputView