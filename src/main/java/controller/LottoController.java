package controller;

import domain.Amount;
import domain.Lotto;
import domain.Lottos;
import domain.Rank;
import domain.dto.LottoResponse;
import domain.dto.LottosResponse;
import domain.factory.LottosFactory;
import domain.WinningLotto;
import domain.dto.ResultResponse;
import domain.generator.RandomGenerator;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;
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
        Lottos lottos = getRandomLottos(amount);
        WinningLotto winningLotto = inputWinningLotto();
        getResult(lottos, winningLotto, amount);
    }

    private Amount inputAmount() {
        String price = inputView.inputPrice();
        Amount amount = new Amount(price);
        outputView.printAmount(amount);
        return amount;
    }

    private Lottos getRandomLottos(Amount amount) {
        LottosFactory lottosFactory = new LottosFactory(new RandomGenerator());
        Lottos lottos = lottosFactory.from(amount);
        outputView.printLottos(getLottosDto(lottos));
        return lottos;
    }

    private LottosResponse getLottosDto(Lottos lottos) {
        List<LottoResponse> lottoResponses = new ArrayList<>();
        for (Lotto lotto : lottos.getLottos()) {
            lottoResponses.add(getLottoDto(lotto));
        }
        return new LottosResponse(lottoResponses);
    }

    private LottoResponse getLottoDto(Lotto lotto) {
        return new LottoResponse(lotto.getNumbers());
    }

    private WinningLotto inputWinningLotto() {
        String winningNumber = inputView.inputWinningLotto();
        String bonusNumber = inputView.inputBonusLotto();
        return new WinningLotto(winningNumber, bonusNumber);
    }

    private void getResult(Lottos lottos, WinningLotto winningLotto, Amount amount) {
        ResultResponse lottosResult = getResultResponse(lottos, winningLotto, amount);
        outputView.printWinningStatistic(lottosResult);
    }

    public ResultResponse getResultResponse(Lottos lottos, WinningLotto winningLotto, Amount amount) {
        EnumMap<Rank, Integer> countRank = lottos.countMatchNumbers(winningLotto);

        long prizeSum = 0L;
        for (Entry<Rank, Integer> rankIntegerEntry : countRank.entrySet()) {
            prizeSum += Rank.calculateTotalPrize(rankIntegerEntry.getKey(), rankIntegerEntry.getValue());
        }

        double profit = amount.calculateProfit(prizeSum);
        return new ResultResponse(countRank, profit);
    }
}
