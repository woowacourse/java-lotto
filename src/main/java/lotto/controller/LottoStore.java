package lotto.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.viewer.InputView;
import lotto.viewer.OutputView;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;

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
        Map<String, Integer> lottoResultStatistics = lottos.getStatistics(winningNumbers, bonusNumber);
        printLottoResult(lottoResultStatistics, lottos);
    }

    public Lottos buyLotto() {
        int possessedMoney = inputView.purchaseMoney();
        Lottos purchasedLottos = new Lottos(calculateAffordableLottoPieces(possessedMoney));
        outputView.printPurchasedLottos(purchasedLottos);
        return purchasedLottos;
    }

    public void printLottoResult(Map<String, Integer> lottoResultStatistics, Lottos lottos) {
        double profitRate = calculateProfitRate(lottoResultStatistics, lottos.getSize());
        outputView.printLottoStatistics(lottoResultStatistics, profitRate);
    }

    public double calculateProfitRate(Map<String, Integer> exampleLottosResult, int lottoPiece) {
        double sum = 0;
        for (Entry<String, Integer> keyValue : exampleLottosResult.entrySet()) {
            sum += LottoRank.tranlateRankToPrizeMoney(keyValue.getKey()) * keyValue.getValue();
        }
        double investCapital = lottoPiece * LOTTO_PRICE;
        double rawProfitRate = sum / investCapital;
        return Math.round(rawProfitRate * 100) / 100.00;
    }

    public int calculateAffordableLottoPieces(int money) {
        return money / LOTTO_PRICE;
    }

}
