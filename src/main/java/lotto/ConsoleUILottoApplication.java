package lotto;

import lotto.domain.BoughtLottos;
import lotto.domain.Result;
import lotto.domain.WinningNumber;
import lotto.domain.generator.ResultGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleUILottoApplication {
    private static final int ONE_THOUSAND = 1000;

    public static void main(String[] args) {
        int buyPrice = cuttingThousandPiece(InputView.inputBuyPrice());
        BoughtLottos boughtLottos = BoughtLottos.buyLottos(buyPrice);
        OutputView.printBoughtLottos(boughtLottos);
        WinningNumber winningNumber = new WinningNumber(InputView.inputWinningNumber());
        Result result = ResultGenerator.generateResult(boughtLottos, winningNumber);
        OutputView.printLottoResult(result, buyPrice);
    }

    private static int cuttingThousandPiece(int buyPrice) {
        return buyPrice - (buyPrice % ONE_THOUSAND);
    }
}
