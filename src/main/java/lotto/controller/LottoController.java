package lotto.controller;

import lotto.domain.LottoSeller;
import lotto.view.InputView;

public class LottoController {
    public void run() {
        LottoSeller lottoSeller = InputView.makeLottoSeller();
    }
}
