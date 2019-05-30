package lotto.controller;

import lotto.domain.LottoAmount;
import lotto.view.inputview.InputView;
import lotto.view.inputview.PriceParser;
import lotto.view.outputview.OutputView;

public class LottoShop {
    public void operate() {
        Integer PurchasePrice = PriceParser.getPurchasePrice(InputView.inputPrice());
        LottoAmount lottoAmount = LottoAmount.createLottoAmount(PurchasePrice);
        OutputView.printAmount(lottoAmount);
    }
}
