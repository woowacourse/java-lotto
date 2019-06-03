package lotto.controller;

import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutPutView;

public class LottoController {

    public void run() {
        String price = InputView.InputPrice();
        LottoTicket lottoTicket = new LottoTicket(price);
        OutPutView.showLottoTicket(lottoTicket);

        String[] winNumber = InputView.InputWinLottoNumber();
//        OutPutView.showLottoResult(new LottoResult(lottoTicket, new WinningLotto(winNumber).getWinningLotto()));
    }
}
