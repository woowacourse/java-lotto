package controller;

import domain.Money;
import domain.Lottos;
import domain.Rank;
import domain.dto.AmountDto;
import domain.dto.LottosDto;
import domain.WinningLotto;
import domain.dto.ResultDto;
import domain.generator.RandomGenerator;
import parser.InputParser;
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

    private Lottos createLottosFromRandomNumber(final Money money) {
        Lottos lottos = money.buyLottos(new RandomGenerator());
        outputView.printLottos(LottosDto.from(lottos));
        return lottos;
    }

    private WinningLotto inputWinningLotto() {
        String winningNumber = inputView.inputWinningLotto();
        String bonusNumber = inputView.inputBonusLotto();

        return new WinningLotto(InputParser.lottoParser(winningNumber), bonusNumber);
    }

    private void calculateResult(final Lottos lottos, final WinningLotto winningLotto, final Money money) {
        EnumMap<Rank, Integer> countRank = lottos.calculateWinningResult(winningLotto);
        double profit = money.calculateProfit(countRank);

        outputView.printWinningStatistic(new ResultDto(countRank, profit));
    }
}
