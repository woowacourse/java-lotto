package controller;

import java.util.List;
import model.WinLotto;
import view.InputView;

public class WinLottoController {
    private final InputView inputView = new InputView();

    public WinLotto winLotto() {
        List<Integer> winNumbers = inputView.readWinNumbers();
        Integer bonusNumber = inputView.readBonusNumber();
        return new WinLotto(winNumbers, bonusNumber);
    }
}

