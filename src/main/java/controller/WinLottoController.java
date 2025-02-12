package controller;

import model.WinLotto;
import view.WinLottoView;

public class WinLottoController {
    private WinLottoView winLottoView = new WinLottoView();

    public WinLotto winLotto() {
        winLottoView.printWinNumberGuide();
        winLottoView.readWinNumbers();
        winLottoView.printBonusNumberGuide();
        return null;

    }
}

