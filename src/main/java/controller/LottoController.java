package controller;

import domain.Buyer;
import domain.Lotto;
import domain.LottoFactory;
import domain.LottoStatistics;
import domain.Money;
import domain.WinningLotto;
import java.util.List;
import util.InputParser;
import util.Validator;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Money money = creaeteMoney();
        List<Lotto> lottos = LottoFactory.createLottos(money);
        Buyer buyer = new Buyer(lottos);
        outputView.displayLottos(money.calculateTotalLotto(), lottos);

        WinningLotto winningLotto = createWinningLotto();
        LottoStatistics lottoStatistics = buyer.countMatchedRanks(winningLotto);
        outputView.displayResult(lottoStatistics.getLottoCounter());

        int profit = lottoStatistics.calculateProfit();
        double profitRate = money.calculateProfitRate(profit);
        outputView.displayProfit(profitRate);
    }

    private Money creaeteMoney() {
        String inputMoney = inputView.inputLottoMoney();
        Validator.inputValidatorIsNull(inputMoney);

        return new Money(InputParser.parseStringToInteger(inputMoney));
    }

    private WinningLotto createWinningLotto() {
        String inputWinningNumber = inputView.inputWinningNumbers();
        Validator.inputValidatorIsNull(inputWinningNumber);

        String inputBonusNumber = inputView.inputBonusNumber();
        Validator.inputValidatorIsNull(inputBonusNumber);

        return new WinningLotto(InputParser.parseStringToList(inputWinningNumber),
            InputParser.parseStringToInteger(inputBonusNumber));
    }
}
