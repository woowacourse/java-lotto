package lotto.controller;

import lotto.domain.lotto.*;
import lotto.view.InputView;
import lotto.view.OutPutView;

import java.util.List;

public class LottoController {

    public void run() {
        Price price = new Price(InputView.InputPrice());
        int numberOfCustomLotto = new NumberOfCustomLotto(InputView.InputNumberOfCustomLotto(), price.getNumberOfLotto()).getNumberOfCustomLotto();
        List<String[]> customLottos = InputView.InputCustomLottoNumber(numberOfCustomLotto);

        LottoTicket lottoTicket = new LottoTicket(price, customLottos);
        OutPutView.showLottoTicket(lottoTicket);

        String[] winNumber = InputView.InputWinLottoNumber();
        String bonusNumber = InputView.InputBonusNumber();
        OutPutView.showLottoResult(new LottoResult(lottoTicket, new WinningLotto(winNumber, bonusNumber)));
    }

}
