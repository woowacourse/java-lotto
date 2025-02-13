package controller;

import domain.AnswerLotto;
import domain.Lottos;
import domain.Money;
import domain.numbergenerator.NumberGenerator;
import domain.numbergenerator.RandomNumberGenerator;
import java.util.List;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        Money money = getMoney();
        List<Integer> selectedNumbers = getSelectedNumbers();
        int bonus = getBonusNumber();
        Lottos lottos = getLottos(money.getBuyableLottoCount());

        OutputView.printLottos(lottos.getOutputLottosDtos());
        AnswerLotto answerLotto = lottoService.getAnswerLotto(selectedNumbers, bonus);
    }

    private Lottos getLottos(int buyableLottoCount) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        return Lottos.of(numberGenerator, buyableLottoCount);
    }

    private int getBonusNumber() {
        String bonusNumber = InputView.inputBonusNumber();
        return InputParser.parseInt(bonusNumber);
    }

    private List<Integer> getSelectedNumbers() {
        String numbers = InputView.inputAnswerNumber();
        return InputParser.parseNumbers(numbers);
    }

    private Money getMoney() {
        String money = InputView.inputMoney();
        return new Money(InputParser.parseInt(money));
    }
}
