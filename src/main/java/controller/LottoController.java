package controller;

import domain.AnswerLotto;
import domain.Lottos;
import domain.Money;
import domain.numbergenerator.NumberGenerator;
import domain.numbergenerator.RandomNumberGenerator;
import dto.OutputLottosDto;
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
        OutputView.printBuyQuantity(money.getBuyableLottoCount());
        OutputView.printChangeMoney(money.getChange());

        Lottos lottos = getLottos(money.getBuyableLottoCount());
        List<OutputLottosDto> outputLottosDtos = lottoService.getOutputLottosDtos(lottos.getLottos());
        OutputView.printLottos(outputLottosDtos);

        List<Integer> answerNumbers = getAnswerNumbers();
        int bonusNumber = getBonusNumber();
        AnswerLotto answerLotto = lottoService.getAnswerLotto(answerNumbers, bonusNumber);

        printPrizeResult(answerLotto, lottos);
    }

    private void printPrizeResult(AnswerLotto answerLotto, Lottos lottos) {
        lottoService.calculatePrize(answerLotto, lottos);
        OutputView.printPrizeResult(lottoService.getPrizeResult());

        double rateOfReturn = lottoService.calculateRateOfReturn();
        OutputView.printRateOfReturn(rateOfReturn);
    }

    private Lottos getLottos(int buyableLottoCount) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        return Lottos.of(numberGenerator, buyableLottoCount);
    }

    private int getBonusNumber() {
        String bonusNumber = InputView.inputBonusNumber();
        return InputParser.parseInt(bonusNumber);
    }

    private List<Integer> getAnswerNumbers() {
        String numbers = InputView.inputAnswerNumber();
        return InputParser.parseNumbers(numbers);
    }

    private Money getMoney() {
        String money = InputView.inputMoney();
        return new Money(InputParser.parseInt(money));
    }
}
