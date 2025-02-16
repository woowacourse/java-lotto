package controller;

import java.util.List;
import model.WinLotto;
import view.InputView;
import view.OutputView;

public class WinLottoController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();

    public WinLotto winLotto() {
        List<Integer> winNumbers = inputView.readWinNumbers();
        Integer bonusNumber = inputView.readBonusNumber();
        return new WinLotto(winNumbers, bonusNumber);
    }
}

