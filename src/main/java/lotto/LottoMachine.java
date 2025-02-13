package lotto;

import java.io.IOException;
import java.util.function.Supplier;
import lotto.domain.AmountPaid;
import lotto.domain.LottoBundle;
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
        LottoBundle lottoBundle = makeLottoBundle();
        outputView.lottoQuantityPrint(lottoBundle.getLottoQuantity());
        outputView.lottoStatusPrint22(lottoBundle);
    }

    private LottoBundle makeLottoBundle() {
        return retryUntilValidInput(() -> {
            try {
                return lottoService.makeLottoBundle(
                        new AmountPaid(Parser.parseToInteger(inputView.purchasePriceInput())));
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        });
    }


    private <T> T retryUntilValidInput(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.errorMessagePrint(e.getMessage());
            }
        }
    }
}
