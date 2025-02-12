package lotto.controller;

import lotto.domain.LottoPrice;
import lotto.util.StringParser;
import lotto.view.InputView;

public class LottoController {

    private final InputView inputView;

    public LottoController(final InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        LottoPrice lottoPrice = getLottoPrice();
        System.out.println();
    }

    private LottoPrice getLottoPrice() {
        int parsedAmount = StringParser.parseInt(inputView.readPurchasePrice());
        return new LottoPrice(parsedAmount);
    }

}
