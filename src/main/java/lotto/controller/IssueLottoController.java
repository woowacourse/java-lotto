package lotto.controller;

import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.Lottoes;
import lotto.view.OutputView;

public class IssueLottoController {

    private final LottoMachine lottoMachine;

    public IssueLottoController(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public Lottoes run() {
        OutputView.printLottoSize(lottoMachine.getManualLottoesSize(),
            lottoMachine.getAutoLottoesSize());
        Lottoes lottoes = lottoMachine.issueLotto();
        for (Lotto lotto : lottoes) {
            OutputView.printLotto(lotto.getIntValues());
        }
        return lottoes;
    }

}
