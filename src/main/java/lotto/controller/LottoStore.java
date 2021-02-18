package lotto.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.viewer.InputView;
import lotto.viewer.OutputView;

public class LottoStore {

    private final static int LOTTO_PRICE = 1000;

    private final InputView inputView;
    private final OutputView outputView;

    public LottoStore() {
        Scanner scanner = new Scanner(System.in);
        inputView = new InputView(scanner);
        outputView = new OutputView();
    }

    public void process() {
        Lottos lottos = buyLotto();
        List<Integer> winningNumbers = inputView.inputWinningNumbers();
        int bonusNumber = inputView.inputBonusNumber();
        Map<LottoRank, Integer> lottoResultStatistics = lottos.getStatistics(winningNumbers, bonusNumber);
        printLottoResult(lottoResultStatistics, lottos);
    }

    public Lottos buyLotto() {
        int receivedMoney = inputView.purchaseMoney();
        Money possessedMoney =new Money(receivedMoney);
        Lottos purchasedLottos = new Lottos(possessedMoney.getLottoPieces(LOTTO_PRICE));
        outputView.printPurchasedLottos(purchasedLottos);
        return purchasedLottos;
    }

    public void printLottoResult(Map<LottoRank, Integer> lottoResultStatistics, Lottos lottos) {
        double profitRate = calculateProfitRate(lottoResultStatistics, lottos.getSize());
        outputView.printLottoStatistics(lottoResultStatistics, profitRate);
    }

    public double calculateProfitRate(Map<LottoRank, Integer> exampleLottosResult, int lottoPiece) {
        double sum = 0;
        for (Entry<LottoRank, Integer> keyValue : exampleLottosResult.entrySet()) {
            sum += keyValue.getKey().getPrizeMoney() * keyValue.getValue();
        }
        double investCapital = lottoPiece * LOTTO_PRICE;
        double rawProfitRate = sum / investCapital;
        return Math.round(rawProfitRate * 100) / 100.00;
    }
}
