package controller;

import domain.AnswerLotto;
import java.util.List;
import service.LottoService;
import view.InputView;

public class LottoController {
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        int money = getMoney();
        List<Integer> selectedNumbers = getSelectedNumbers();
        int bonus = getBonusNumber();

        AnswerLotto answerLotto = lottoService.getAnswerLotto(selectedNumbers, bonus);
    }

    private int getBonusNumber() {
        String bonusNumber = InputView.inputBonusNumber();
        return InputParser.parseInt(bonusNumber);
    }

    private List<Integer> getSelectedNumbers() {
        String numbers = InputView.inputAnswerNumber();
        return InputParser.parseNumbers(numbers);
    }

    private int getMoney() {
        String money = InputView.inputMoney();
        return InputParser.parseInt(money);
    }
}
