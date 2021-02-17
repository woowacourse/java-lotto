package lotto.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import lotto.domain.LottoRank;
import lotto.viewer.InputView;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;
    private final InputView inputView;

    public LottoStore() {
        Scanner scanner = new Scanner(System.in);
        inputView = new InputView(scanner);
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
