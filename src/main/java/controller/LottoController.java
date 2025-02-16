package controller;

import domain.Amount;
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
        Amount amount = inputAmount();
        Lottos lottos = createLottosFromRandomNumber(amount);
        WinningLotto winningLotto = inputWinningLotto();
        calculateResult(lottos, winningLotto, amount);
    }

    private Amount inputAmount() {
        int price = inputView.inputPrice();
        Amount amount = new Amount(price);
        outputView.printAmount(new AmountDto(amount));

        return amount;
    }

    private Lottos createLottosFromRandomNumber(Amount amount) {
        LottosFactory lottosFactory = new LottosFactory(new RandomGenerator());
        Lottos lottos = lottosFactory.from(amount);
        outputView.printLottos(LottosDto.from(lottos));
        return lottos;
    }

    private WinningLotto inputWinningLotto() {
        String winningNumber = inputView.inputWinningLotto();
        String bonusNumber = inputView.inputBonusLotto();

        return new WinningLotto(winningNumber, bonusNumber);
    }

    private void calculateResult(Lottos lottos, WinningLotto winningLotto, Amount amount) {
        EnumMap<Rank, Integer> countRank = lottos.calculateResultOfWinning(winningLotto);
        double profit = amount.calculateProfit(countRank);

        outputView.printWinningStatistic(new ResultDto(countRank, profit));
    }
}
