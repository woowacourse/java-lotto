package lotto;

import lotto.domain.*;
import lotto.domain.generator.ResultGenerator;
import lotto.view.OutputView;

import static lotto.domain.generator.LottoNumbersGenerator.generateLottoNumbers;
import static lotto.view.InputView.*;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        Money money = new Money(inputBuyPrice());
        int buyPrice = money.getBuyPrice();

        int countOfManualBuyLotto = inputManualBuyLottoCount(buyPrice);
        BoughtLottos boughtLottos = BoughtLottos.buyLottos(money, inputManualLottos(countOfManualBuyLotto));
        OutputView.printBoughtLottos(boughtLottos);

        WinningNumber winningNumber = new WinningNumber(new Lotto(
                generateLottoNumbers(inputLottoNumber(INPUT_WINNING_NUMBER_MESSAGE))),
                inputBonusBall());

        Result result = ResultGenerator.generateResult(boughtLottos, winningNumber);
        OutputView.printLottoResult(result, buyPrice);
    }
}
