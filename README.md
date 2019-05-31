# java-lotto

# 기능 구현목록
- 사용자는 로또구입금액을 입력한다. -> InputView
    - 구입금액은 1000원 이상이어야 한다. -> Price
 
- 사용자는 수동으로 구매할 로또 수를 입력한다. -> InputView
    - 로또 수는 0이상 구입 한 로또 수 이하이다. -> SelfLotto 

- 사용자는 수동으로 구매할 번호를 입력한다. -> InputView
    - 로또번호는 6개를 입력해야한다. -> Lotto
    - 로또번호는 1~45사이의 수를 입력해야 한다. -> Number
    - 로또번호는 중복을 허용하지 않는다.   -> Lotto

- 수동으로 고른 번호는 쉼표로 분리된다. -> InputView
    - 구분자는 무조건 쉼표여야만 한다.    -> InputView

- 자동으로 로또번호 6개를 만든다.    -> AutoLotto

- 수동,자동으로 구매한 로또들을 출력한다.    -> OutputView

- 사용자는 지난 주 당첨 번호를 입력한다.    -> InputView
    - 로또번호는 6개를 입력해야한다.     -> Lotto
    - 로또번호는 1~45사이의 수를 입력해야 한다. -> Number
    - 로또번호는 중복을 허용하지 않는다.   -> Lotto
    
- 사용자는 보너스 볼을 입력한다.     -> InputView
    - 보너스 볼은 1~45사이의 수이다.   -> Number
    - 보너스 볼은 지난주 당첨번호에 없는 수여야 한다.   -> WinningLotto

- 사용자가 구한 로또와 당첨번호 + 보너스볼을 비교하여 결과를 구한다.

- 사용자의 결과에 따라 당첨금액을 구한다.

- 수익룰을 구한다.

- 전체 결과를 출력한다.

- 수익률을 출력한다.    