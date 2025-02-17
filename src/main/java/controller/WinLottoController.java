package controller;

import java.util.List;
import model.WinLotto;
import view.InputView;

public class WinLottoController {
    private final InputView inputView;

    public WinLottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public WinLotto winLotto() {
        List<Integer> winNumbers = inputView.readWinNumbers();
        Integer bonusNumber = inputView.readBonusNumber();
        return new WinLotto(winNumbers, bonusNumber);
    }
}

