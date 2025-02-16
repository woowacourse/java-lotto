package controller;

import domain.Amount;
import domain.Lotto;
import domain.Lottos;
import domain.Rank;
import domain.WinningLotto;
import domain.dto.LottoResponse;
import domain.dto.LottosResponse;
import domain.dto.ResultResponse;
import domain.factory.LottosFactory;
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

        Lottos lottos = createRandomLottos(amount);
        printRandomLottos(lottos);

        WinningLotto winningLotto = inputWinningLotto();

        ResultResponse lottoResult = createLottoResultResponse(lottos, winningLotto, amount);
        printLottoResult(lottoResult);
    }

    private Amount inputAmount() {
        String price = inputView.inputPrice();
        Amount amount = new Amount(price);
        outputView.printAmount(amount);
        return amount;
    }

    private Lottos createRandomLottos(Amount amount) {
        LottosFactory lottosFactory = new LottosFactory(new RandomGenerator());
        return lottosFactory.from(amount);
    }

    private void printRandomLottos(Lottos lottos) {
        outputView.printLottos(createLottosDto(lottos));
    }

    private LottosResponse createLottosDto(Lottos lottos) {
        List<LottoResponse> lottoResponses = new ArrayList<>();
        for (Lotto lotto : lottos.getLottos()) {
            lottoResponses.add(createLottoDto(lotto));
        }
        return new LottosResponse(lottoResponses);
    }

    private LottoResponse createLottoDto(Lotto lotto) {
        return new LottoResponse(lotto.getNumbers());
    }

    private WinningLotto inputWinningLotto() {
        List<Integer> winningLotto = inputView.inputWinningLotto();
        String bonusNumber = inputView.inputBonusLotto();
        return new WinningLotto(winningLotto, bonusNumber);
    }

    private ResultResponse createLottoResultResponse(Lottos lottos, WinningLotto winningLotto, Amount amount) {
        EnumMap<Rank, Integer> countRank = lottos.countMatchNumbers(winningLotto);

        long prizeSum = 0L;
        for (Entry<Rank, Integer> rankIntegerEntry : countRank.entrySet()) {
            prizeSum += Rank.calculateTotalPrize(rankIntegerEntry.getKey(), rankIntegerEntry.getValue());
        }

        double profit = amount.calculateProfit(prizeSum);
        return new ResultResponse(countRank, profit);
    }

    private void printLottoResult(ResultResponse lottoResult) {
        outputView.printWinningStatistic(lottoResult);
    }
}
