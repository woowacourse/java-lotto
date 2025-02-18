package presentation;

import domain.AnswerLotto;
import domain.Lottos;
import domain.Money;
import domain.enums.Prize;
import domain.numbergenerator.NumberGenerator;
import domain.numbergenerator.RandomNumberGenerator;
import dto.OutputPurchasedLottosDto;
import java.util.List;
import java.util.Map;
import service.LottoService;
import presentation.view.InputView;
import presentation.view.OutputView;

public class LottoController {
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        try {
            Money money = generateMoney();

            Lottos lottos = getLottos(money.getBuyableLottoCount());
            List<OutputPurchasedLottosDto> outputPurchasedLottosDtos = lottoService.getOutputLottosDtos(lottos.getLottos());
            OutputView.printLottos(outputPurchasedLottosDtos);

            List<Integer> answerNumbers = getAnswerNumbers();
            int bonusNumber = getBonusNumber();
            AnswerLotto answerLotto = lottoService.getAnswerLotto(answerNumbers, bonusNumber);

            printPrizeResult(answerLotto, lottos);
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
        }
    }

    private Money generateMoney() {
        Money money = getMoney();
        OutputView.printBuyQuantity(money.getBuyableLottoCount());
        OutputView.printChangeMoney(money.getChange());
        return money;
    }

    private void printPrizeResult(AnswerLotto answerLotto, Lottos lottos) {
        Map<Prize, Integer> prizeResult = lottoService.calculatePrize(answerLotto, lottos);
        OutputView.printPrizeResult(prizeResult);

        double rateOfReturn = lottoService.calculateRateOfReturn(prizeResult);
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
