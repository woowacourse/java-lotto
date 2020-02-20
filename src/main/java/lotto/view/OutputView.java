package lotto.view;

import java.util.Map;

import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.Rank;

public class OutputView {
    public static void inputMoneyInstruction() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void ticketAmountInstruction(Money money) {
        if (money.change() != 0) {
            System.out.println("거스름돈 " + money.change() + "원은 돌려드립니다.");
        }
        System.out.println(money.ticketQuantity() + "개를 구매했습니다.");
    }

    public static void lottoTicketList(LottoTickets lottoTickets) {
        System.out.println(lottoTickets + "\n");
    }

    public static void inputWinningNumberInstruction() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void inputBonusNumberInstruction() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void prizeStatistics(Map<Rank, Integer> results) {
        String sb = "\n당첨 통계\n---------\n" + prizeStatistic(results, Rank.FOURTH)
            + prizeStatistic(results, Rank.THIRD)
            + prizeStatistic(results, Rank.SECOND)
            + prizeStatistic(results, Rank.BONUS)
            + prizeStatistic(results, Rank.FIRST);
        System.out.println(sb);
    }

    private static String prizeStatistic(Map<Rank, Integer> results, Rank rank) {
        return format(rank) + results.getOrDefault(rank, 0) + "개\n";
    }

    private static String format(Rank rank) {
        if (rank == Rank.BONUS) {
            return "5개 일치, 보너스 볼 일치 (" + rank.getPrize() + "원)- ";
        }
        return rank.getMatchCount() + "개 일치 (" + rank.getPrize() + "원)- ";
    }

    public static void profitRate(String rate) {
        System.out.println("총 수익률은 " + rate + "%입니다.");
    }

    public static void errorMessage(String message) {
        System.out.println(message);
    }
}
