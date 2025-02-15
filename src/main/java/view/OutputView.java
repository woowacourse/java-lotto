package view;

import java.util.Arrays;
import model.OwnedLotto;
import model.result.PrizeResult;
import model.result.Rank;

public class OutputView {
    public static final String SHOW_BUY_COUNT = "%d개를 구매했습니다.\n";
    public static final String SHOW_STATISTICS = "당첨 통계";
    public static final String SHOW_DELIMITER = "---------";
    public static final String RESULT_RANK5 = "3개 일치 (5000원)- ";
    public static final String RESULT_RANK4 = "4개 일치 (50000원)- ";
    public static final String RESULT_RANK3 = "5개 일치 (1500000원)- ";
    public static final String RESULT_RANK2 = "5개 일치, 보너스 볼 일치(30000000원) - ";
    public static final String RESULT_RANK1 = "6개 일치 (2000000000원)- ";
    public static final String SHOW_PRIZE_RATE = "총 수익률은 %.2f입니다.\n";

    public void displayPrizeSummary(PrizeResult result) {
        displayPrizeSummaryIntro();
        StringBuilder sb = new StringBuilder();

        // Rank.values()를 통해 각 랭크에 대해 처리
        Arrays.stream(Rank.values())
                .filter(rank -> !isMiss(rank))
                .forEach(rank -> sb.append(getMsg(rank)).append(findByRank(result, rank)).append("개\n"));

        System.out.print(sb);
        System.out.printf(SHOW_PRIZE_RATE, result.calculateProfit());
    }

    public String getMsg(Rank rank) {
        if (rank.name().equals("RANK1")) {
            return RESULT_RANK1;
        }
        if (rank.name().equals("RANK2")) {
            return RESULT_RANK2;
        }
        if (rank.name().equals("RANK3")) {
            return RESULT_RANK3;
        }
        if (rank.name().equals("RANK4")) {
            return RESULT_RANK4;
        }
        if (rank.name().equals("RANK5")) {
            return RESULT_RANK5;
        }
        return null;
    }

    public int findByRank(PrizeResult result, Rank rank) {
        return result.getResult().getOrDefault(rank, 0);  // result에서 rank에 해당하는 값 반환, 없으면 0
    }

    public boolean isMiss(Rank rank) {
        return rank == Rank.MISS;
    }


    private void displayPrizeSummaryIntro() {
        displaySpacing();
        System.out.println(SHOW_STATISTICS);
        System.out.println(SHOW_DELIMITER);
    }

    public void displayLottoNumbers(OwnedLotto ownedLotto) {
        System.out.printf(SHOW_BUY_COUNT, ownedLotto.size());
        System.out.printf("%s", ownedLotto.toString());
        displaySpacing();
    }

    public void displayErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        displaySpacing();
    }

    public void displaySpacing() {
        System.out.println();
    }
}