package view;

import static view.DisplayConstants.SHOW_BUY_COUNT;
import static view.DisplayConstants.SHOW_DELIMITER;
import static view.DisplayConstants.SHOW_PRIZE_RATE;
import static view.DisplayConstants.SHOW_RESULT_RANK1;
import static view.DisplayConstants.SHOW_RESULT_RANK2;
import static view.DisplayConstants.SHOW_RESULT_RANK3;
import static view.DisplayConstants.SHOW_RESULT_RANK4;
import static view.DisplayConstants.SHOW_RESULT_RANK5;
import static view.DisplayConstants.SHOW_STATISTICS;

import java.util.Arrays;
import java.util.Map;
import model.PurchasedLotto;
import model.result.PrizeResult;
import model.result.Rank;

public class OutputView {
    public void displayLottoNumbers(PurchasedLotto purchasedLotto) {
        System.out.println(SHOW_BUY_COUNT.format(purchasedLotto.size()));
        System.out.printf("%s", purchasedLotto.toString());
        displaySpacing();
    }

    private void displayPrizeSummaryIntro() {
        displaySpacing();
        System.out.println(SHOW_STATISTICS.getFormat());
        System.out.println(SHOW_DELIMITER.getFormat());
    }

    public void displayPrizeSummary(PrizeResult result) {
        displayPrizeSummaryIntro();
        StringBuilder sb = new StringBuilder();

        Arrays.stream(Rank.values())
                .filter(rank -> !isMiss(rank))
                .forEach(rank -> sb.append(getMsg(rank)).append(findByRank(result, rank)).append("개\n"));

        System.out.print(sb);
        System.out.println(SHOW_PRIZE_RATE.format(result.calculateProfit()));
    }

    private static final Map<Rank, String> rankMessages = Map.of(
            Rank.RANK1, SHOW_RESULT_RANK1.getFormat(),
            Rank.RANK2, SHOW_RESULT_RANK2.getFormat(),
            Rank.RANK3, SHOW_RESULT_RANK3.getFormat(),
            Rank.RANK4, SHOW_RESULT_RANK4.getFormat(),
            Rank.RANK5, SHOW_RESULT_RANK5.getFormat()
    );

    public boolean isMiss(Rank rank) {
        return rank == Rank.MISS;
    }

    public String getMsg(Rank rank) {
        return rankMessages.get(rank);
    }

    public int findByRank(PrizeResult result, Rank rank) {
        return result.getResult().getOrDefault(rank, 0);  // result에서 rank에 해당하는 값 반환, 없으면 0
    }

    public void displayErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        displaySpacing();
    }

    public void displaySpacing() {
        System.out.println();
    }
}