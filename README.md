# java-lotto
로또 미션 진행을 위한 저장소

## 기능 요구사항

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.
- 사용자가 수동으로 추첨 번호를 입력할 수 있도록 해야 한다.
- 입력한 금액, 자동 생성 숫자, 수동 생성 번호를 입력하도록 해야 한다.


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
- 예외가 발생하는 부분에 대해 자바 Exception을 적용해 예외처리한다.
- 사용자가 입력한 값에 대한 예외 처리를 철저히 한다.
---

## 클래스 구조

## Domain(Model)

- **PurchaseAmount**
    - int amount
    - [예외처리] 구매 금액
        - checkNotNumber 숫자인지 아닌지 검증
        - checkNegativeNumber 음수인지 검증
        - checkUnderLottoPrice 로또 한 장 가격보다 낮은 금액인지 검증
- **LottoFactory**
    - [예외 처리] 난수가 1~45까지 수 인지 검증
- **RandomNumberGenerator**
    - 1부터 45 사이의 난수 생성
- **LottoNumber**
    - int number
    - [예외 처리] checkLottoNumber 1부터 45까지 숫자인지 검증
    - [예외 처리] checkNotNumber  입력된 번호 중 숫자가 아닌 문자가 들어왔을 경우 검증
- **Lotto**
    - List<LottoNumber> lotto
    - [예외 처리] checkLottoSizeSix 6개의 숫자가 만들어졌는 지 검증
- **Lottos**
    - List<Lotto> lottoBundle
- **LottosFactory**
    - Lottos 생
- **LottoRank**
    - FIRST(6, false, 2000000000, "6개 일치(2000000000원) - ")
    - SECOND(5, true, 30000000, "5개 일치, 보너스볼 일치(30000000원) - ")
    - THIRD(5, false, 1500000, "5개 일치(1500000원) - ")
    - FOURTH(4, false, 50000, "4개 일치(50000원) - ")
    - FIFTH(3, false, 5000, "3개 일치(5000원) - ")
    - 당첨번호 매칭갯수와, 보너스볼 매칭여부 결과로 Enum 반환
- **LottoResult**
    - Map<LottoRank, Integer> result
    - 로또 한 개별 당첨 결과 카운트 증가
    - 총 수익 계산 및 반환 
- **WinningNumber**
    - List<LottoNumber> winningNumber
    - int bonusNumber
    - [예외 처리] checkLottoNull 파라미터로 들어온 lotto가 null인지 검증
    - [예외 처리] checkDuplicatedLottoNumber 당첨번호에 보너스볼과 중복된 번호가 있는지 검증
- **Profit**
    - int LOTTO_PRICE
    - 로또가격을 기준으로 수익률 계산
    
    
## Controller

- **LottoGame**
    - main
    - 입력 예외 발생 시 재입력 요청
    
    
## View

- InputView
    - inputPurchaseAmount
        - String 으로 반환
    - inputManualCount
        - 수동으로 구매할 로또 갯수 입력
        - String으로 반환
    - inputManualLottoNumbers
        - 수동으로 구매할 로또 번호 입력
        - ","로 split 후 String[] 반
    - inputWinningNumbers
        - ","로 split 후 String[] 반환
    - inputBonusNumber
        - String 으로 반환
- OutputView
    - 구매 금액 입력 메세지 출력
    - 로또 구매 확인 메세지 출력 (수동 + 자동)
    - 구매한 모든 로또 번호 출력
    - 당청 번호 입력 메세지 출력
    - 보너스 번호 입력 메세지 출력
    - 당첨 결과 출력
    - 최종 수익률 출력
    - 예외 발생시 에러 메세지 출력
    