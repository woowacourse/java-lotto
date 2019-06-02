package lotto.controller;

import lotto.domain.lotto.LottoTicket;
import lotto.view.InputView;

public class LottoController {

    public void run(){
        String price = InputView.InputPrice();

        LottoTicket lottoTicket = new LottoTicket(price);
        System.out.println(lottoTicket.toString());
    }
}
