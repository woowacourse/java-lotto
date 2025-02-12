package lotto.controller;

import lotto.domain.LottoPrice;
import lotto.util.StringParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        LottoPrice lottoPrice = getLottoPrice();
        int lottoCount = getLottoCount(lottoPrice);
    }

    private LottoPrice getLottoPrice() {
        int parsedAmount = StringParser.parseInt(inputView.readPurchasePrice());
        return new LottoPrice(parsedAmount);
    }

    private int getLottoCount(final LottoPrice lottoPrice) {
        int lottoCount = lottoPrice.calculateLottoCount();
        outputView.printLottoCount(lottoCount);
        return lottoCount;
    }

}
