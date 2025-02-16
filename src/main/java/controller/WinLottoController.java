package controller;

import java.util.List;
import model.WinLotto;
import view.WinLottoView;

public class WinLottoController {
    private WinLottoView winLottoView = new WinLottoView();

    public WinLotto winLotto() {
        winLottoView.printWinNumberGuide();
        List<Integer> winNumbers = winLottoView.readWinNumbers();
        winLottoView.printBonusNumberGuide();
        int bonusNumber = winLottoView.readBonusNumber();
        return new WinLotto(winNumbers, bonusNumber);
    }
}

