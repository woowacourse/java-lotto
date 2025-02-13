package lotto.view;

import static lotto.common.Constants.ENTER;

import lotto.domain.Lotto;
import lotto.domain.LottoGroup;
import lotto.domain.Profit;
import lotto.domain.Rank;

public class OutputView {

    private OutputView() {
    }

    public static void printError(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    public static void printLottoGroup(LottoGroup lottoGroup) {
        System.out.printf("%d개를 구매했습니다." + ENTER, lottoGroup.getSize());

        for (Lotto lotto : lottoGroup.getLottoGroup()) {
            System.out.println(lotto.toString());
        }

        System.out.println();
    }

    public static void printResult(Profit profit, String profitRate) {
        printNoticeResultMessage();
        printMatchCounts(profit);
        printProfitRate(profitRate);
    }

    private static void printNoticeResultMessage() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private static void printMatchCounts(Profit profit) {
        profit.getRankMap()
                .forEach((rank, matchCount) -> {
                    if (rank.equals(Rank.NO_REWARD)) {
                        return;
                    }
                    System.out.printf("%s%s개" + ENTER, rank.getMessage(), matchCount);
                });
    }

    private static void printProfitRate(String profitRate) {
        System.out.printf(ENTER + "총 수익률은 %s입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", profitRate);
    }
}

