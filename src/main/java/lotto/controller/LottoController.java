package lotto.controller;

import lotto.domain.LottoSeller;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        LottoSeller lottoSeller = InputView.makeLottoSeller();
        OutputView.showNumOfTicketsFrom(lottoSeller);
    }
}
