package lotto;

import lotto.domain.BoughtLottos;
import lotto.domain.Lotto;
import lotto.domain.Result;
import lotto.domain.WinningNumber;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.generator.ResultGenerator;
import lotto.view.OutputView;

import java.util.List;

import static lotto.view.InputView.*;

public class ConsoleUILottoApplication {
    private static final int ONE_THOUSAND = 1000;

    public static void main(String[] args) {
        int buyPrice = cuttingThousandPiece(inputBuyPrice());
        int countOfManualBuyLotto = inputManualBuyLottoCount(buyPrice);
        List<Lotto> manualBoughtLottos = LottoGenerator.generateManualLottos(inputManualLottos(countOfManualBuyLotto));
        BoughtLottos boughtLottos = BoughtLottos.buyLottos(buyPrice, manualBoughtLottos);
        OutputView.printBoughtLottos(boughtLottos);
        WinningNumber winningNumber = new WinningNumber(LottoGenerator.generateManualLotto(
                inputLottoNumber(INPUT_WINNING_NUMBER_MESSAGE)), inputBonusBall());
        Result result = ResultGenerator.generateResult(boughtLottos, winningNumber);
        OutputView.printLottoResult(result, buyPrice);
    }

    private static int cuttingThousandPiece(int buyPrice) {
        return buyPrice - (buyPrice % ONE_THOUSAND);
    }
}
