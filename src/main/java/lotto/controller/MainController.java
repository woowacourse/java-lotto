package lotto.controller;

import lotto.model.CustomLottoNumbers;
import lotto.model.LottoStore;
import lotto.model.LottoTickets;
import lotto.model.PurchaseAmount;
import lotto.view.InputView;

public class MainController {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = InputView.inputPurchaseAmount();
        CustomLottoNumbers customLottoNumbers = InputView.inputCustomLottoNumbers(InputView.inputPurchaseQuantity());

        LottoTickets lottoTickets = LottoStore.makeLottoTickets(customLottoNumbers, purchaseAmount);

        System.out.println(lottoTickets);
    }
}
