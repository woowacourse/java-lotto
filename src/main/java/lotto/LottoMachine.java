package lotto;

import java.io.IOException;
import java.util.EnumMap;
import java.util.function.Supplier;
import lotto.domain.AmountPaid;
import lotto.domain.LottoBundle;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.exception.LottoException;
import lotto.service.LottoManager;
import lotto.utils.Parser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMachine {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoManager lottoManager;

    public LottoMachine(InputView inputView, OutputView outputView, LottoManager lottoManager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoManager = lottoManager;
    }

    public void run() {
        AmountPaid amountPaid = makeAmountPaid();
        LottoBundle lottoBundle = makeLottoBundle(amountPaid);
        outputView.lottoQuantityPrint(lottoBundle.getLottoQuantity());
        outputView.lottoStatusPrint(lottoBundle);

        WinningNumbers winningNumbers = makeWinningNumber();
        EnumMap<Rank, Integer> lottoResult = lottoManager.makeStatistics(lottoBundle, winningNumbers);
        outputView.lottoStatisticsPrint(lottoResult, lottoManager.calculateTotalResult(lottoResult, amountPaid));
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

    private LottoBundle makeLottoBundle(AmountPaid amountPaid) {
        return retryUntilValidInput(() -> lottoManager.makeLottoBundle(amountPaid));
    }

    private WinningNumbers makeWinningNumber() {
        return retryUntilValidInput(() -> {
            try {
                return lottoManager.makeWinningNumbers(inputView.winningNumberInput(), inputView.bonusNumberInput());
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
