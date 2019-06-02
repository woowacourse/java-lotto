package lotto.controller;

import lotto.domain.lotto.LottoTicket;
import lotto.view.InputView;
import lotto.view.OutPutView;

public class LottoController {

    public void run(){
        String price = InputView.InputPrice();

        LottoTicket lottoTicket = new LottoTicket(price);
        OutPutView.showLottoTicket(lottoTicket);
    }
}
