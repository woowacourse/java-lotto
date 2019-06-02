package lotto.controller;

import lotto.domain.lotto.Price;
import lotto.view.InputView;

public class LottoController {

    public void run(){
        Price price = new Price(InputView.InputPrice());
    }
}
