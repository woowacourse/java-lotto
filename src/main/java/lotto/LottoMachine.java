package lotto;

import java.io.IOException;
import java.util.EnumMap;
import java.util.function.Supplier;
import lotto.domain.AmountPaid;
import lotto.domain.LottoBundle;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.exception.LottoException;
import lotto.service.LottoService;
import lotto.utils.Parser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMachine {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoMachine(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        AmountPaid amountPaid = makeAmountPaid();
        LottoBundle lottoBundle = makeLottoBundle(amountPaid);
        outputView.lottoQuantityPrint(lottoBundle.getLottoQuantity());
        outputView.lottoStatusPrint(lottoBundle);
        WinningNumbers winningNumbers = makeWinningNumber();
        EnumMap<Rank, Integer> lottoResult = lottoBundle.makeStatistics(winningNumbers);

        outputView.lottoStatisticsPrint(lottoResult, lottoService.calculateTotalResult(lottoResult, amountPaid));
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
        return retryUntilValidInput(() -> lottoService.makeLottoBundle(amountPaid));
    }

    private WinningNumbers makeWinningNumber() {
        return retryUntilValidInput(() -> {
            try {
                return lottoService.makeWinningNumbers(inputView.winningNumberInput(), inputView.bonusNumberInput());
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
