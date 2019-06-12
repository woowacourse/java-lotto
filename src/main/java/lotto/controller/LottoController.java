package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutPutView;

import java.util.List;

public class LottoController {

    public void run() {
        Price price = new Price(InputView.InputPrice());
        NumberOfCustomLotto manualAmount = new NumberOfCustomLotto(InputView.InputNumberOfCustomLotto(), price);
        List<String> manualNumber = InputView.InputCustomLottoNumber(manualAmount);

        LottoTicket lottoTicket = new LottoTicket(price, manualNumber);
        OutPutView.showLottoTicket(lottoTicket);

        String[] winNumber = InputView.InputWinLottoNumber();
        String bonusNumber = InputView.InputBonusNumber();
        OutPutView.showLottoResult(new LottoResult(lottoTicket, new WinningLotto(winNumber, bonusNumber)));
    }

}
