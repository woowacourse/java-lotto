package controller;

import domain.Buyer;
import domain.LottoFactory;
import domain.LottoRank;
import domain.LottoStatistics;
import domain.Money;
import domain.WinningLotto;
import java.util.Map;
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
        LottoFactory lottoFactory = new LottoFactory();
        Buyer buyer = new Buyer(lottoFactory.createLottos(money));
        outputView.displayLottos(money.calculateTotalLotto(), buyer.createResult());

        WinningLotto winningLotto = createWinningLotto();
        Map<LottoRank, Integer> lottoResult = buyer.countMatchedRanks(winningLotto);
        outputView.displayResult(lottoResult);

        LottoStatistics lottoStatistics = new LottoStatistics(lottoResult);
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
