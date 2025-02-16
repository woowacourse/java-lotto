package lotto.controller;

import java.io.IOException;
import java.util.function.Supplier;
import lotto.domain.AmountPaid;
import lotto.domain.LottoBundle;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoResult;
import lotto.domain.WinningNumbers;
import lotto.exception.LottoException;
import lotto.utils.Parser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        AmountPaid amountPaid = makeAmountPaid();
        LottoGenerator lottoGenerator = new LottoGenerator();
        LottoBundle lottoBundle = lottoGenerator.makeLottoBundle(amountPaid, amountPaid.getLottoQuantity());

        outputView.lottoQuantityPrint(lottoBundle.getLottoQuantity());
        outputView.lottoStatusPrint(lottoBundle.getLottoBundle());

        WinningNumbers winningNumbers = makeWinningNumber(lottoGenerator);
        LottoResult lottoResult = new LottoResult(lottoBundle.makeStatistics(winningNumbers));

        outputView.lottoStatisticsPrint(lottoResult.getResult(),
                amountPaid.calculateProfitRate(lottoResult.calculateTotalPrize()));
    }


    private AmountPaid makeAmountPaid() {
        return retryUntilValidInput(() -> {
            try {
                return new AmountPaid(Parser.parseToInteger(inputView.purchasePriceInput()));
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        });
    }

    private WinningNumbers makeWinningNumber(LottoGenerator lottoGenerator) {
        return retryUntilValidInput(() -> {
            try {
                return lottoGenerator.makeWinningNumbers(inputView.winningNumberInput(), inputView.bonusNumberInput());
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        });
    }


    private <T> T retryUntilValidInput(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (LottoException e) {
                outputView.errorMessagePrint(e.getMessage());
            }
        }
    }
}
