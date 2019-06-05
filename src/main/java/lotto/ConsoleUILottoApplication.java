package lotto;

import lotto.domain.BoughtLottos;
import lotto.domain.Lotto;
import lotto.domain.Result;
import lotto.domain.WinningNumber;
import lotto.domain.generator.LottosManualGenerator;
import lotto.domain.generator.ResultGenerator;
import lotto.view.OutputView;

import java.util.List;

import static lotto.domain.generator.LottoNumbersGenerator.generateLottoNumbers;
import static lotto.view.InputView.*;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        int buyPrice = cuttingThousandPiece(inputBuyPrice());
        int countOfManualBuyLotto = inputManualBuyLottoCount(buyPrice);
        List<Lotto> manualBoughtLottos = new LottosManualGenerator(
                inputManualLottos(countOfManualBuyLotto))
                .generate();
        BoughtLottos boughtLottos = BoughtLottos.buyLottos(buyPrice, manualBoughtLottos);
        OutputView.printBoughtLottos(boughtLottos);

        WinningNumber winningNumber = new WinningNumber(new Lotto(
                generateLottoNumbers(inputLottoNumber(INPUT_WINNING_NUMBER_MESSAGE))),
                inputBonusBall());

        Result result = ResultGenerator.generateResult(boughtLottos, winningNumber);
        OutputView.printLottoResult(result, buyPrice);
    }

    private static int cuttingThousandPiece(int buyPrice) {
        return buyPrice - (buyPrice % BoughtLottos.BUY_PRICE);
    }
}
