package controller;

import domain.Money;
import domain.Lottos;
import domain.Rank;
import domain.dto.AmountDto;
import domain.dto.LottosDto;
import global.factory.LottosFactory;
import domain.WinningLotto;
import domain.dto.ResultDto;
import global.generator.RandomGenerator;
import java.util.EnumMap;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        Money money = inputAmount();
        Lottos lottos = createLottosFromRandomNumber(money);
        WinningLotto winningLotto = inputWinningLotto();
        calculateResult(lottos, winningLotto, money);
    }

    private Money inputAmount() {
        int price = inputView.inputPrice();
        Money money = new Money(price);
        outputView.printAmount(new AmountDto(money));

        return money;
    }

    private Lottos createLottosFromRandomNumber(Money money) {
        Lottos lottos = money.buyLottos();
        outputView.printLottos(LottosDto.from(lottos));
        return lottos;
    }

    private WinningLotto inputWinningLotto() {
        String winningNumber = inputView.inputWinningLotto();
        String bonusNumber = inputView.inputBonusLotto();

        return new WinningLotto(winningNumber, bonusNumber);
    }

    private void calculateResult(Lottos lottos, WinningLotto winningLotto, Money money) {
        EnumMap<Rank, Integer> countRank = lottos.calculateResultOfWinning(winningLotto);
        double profit = money.calculateProfit(countRank);

        outputView.printWinningStatistic(new ResultDto(countRank, profit));
    }
}
