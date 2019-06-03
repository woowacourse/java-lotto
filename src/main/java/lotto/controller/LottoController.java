package lotto.controller;

import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.NumberOfCustomLotto;
import lotto.domain.lotto.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutPutView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private List<String[]> customLottos;

    public LottoController() {
        customLottos = new ArrayList<>();
    }

    public void run() {
        String price = InputView.InputPrice();
        int numberOfCustomLotto = new NumberOfCustomLotto(InputView.InputNumberOfCustomLotto()).getNumberOfCustomLotto();
        for (int i = 0; i < numberOfCustomLotto; i++) {
            customLottos.add(InputView.InputCustomLottoNumber());
        }

        LottoTicket lottoTicket = new LottoTicket(price, customLottos);
        OutPutView.showLottoTicket(lottoTicket);

        String[] winNumber = InputView.InputWinLottoNumber();
        String bonusNumber = InputView.InputBonusNumber();
        OutPutView.showLottoResult(new LottoResult(lottoTicket, new WinningLotto(winNumber, bonusNumber)));
    }
}
