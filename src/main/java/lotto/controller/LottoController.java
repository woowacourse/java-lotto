package lotto.controller;

import lotto.domain.lotto.LottoTicket;
import lotto.view.InputView;
import lotto.view.OutPutView;

public class LottoController {

    public void run(){
        String price = InputView.InputPrice();
        OutPutView.showLottoTicket(new LottoTicket(price));

        String winNumber = InputView.InputWinLottoNumber();
        
    }
}
