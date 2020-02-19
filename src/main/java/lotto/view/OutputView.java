package lotto.view;

import java.util.Map;

import lotto.domain.LottoTickets;

public class OutputView {
    public static void inputMoneyInstruction() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void ticketAmountInstruction(int quantity) {
        System.out.println(quantity + "개를 구매했습니다.");
    }

    public static void lottoTicketList(LottoTickets lottoTickets) {
        System.out.println(lottoTickets);
    }

    public static void inputWinningNumberInstruction() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void inputBonusNumberInstruction() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void prizeStatistics(Map<Integer, Integer> results) {
        StringBuilder sb = new StringBuilder("\n당첨 통계\n--------\n");
        sb.append("3개 일치 (5000원)- ").append(results.getOrDefault(3, 0)).append("개\n");
        sb.append("4개 일치 (50000원)- ").append(results.getOrDefault(4, 0)).append("개\n");
        sb.append("5개 일치 (150000원)- ").append(results.getOrDefault(5, 0)).append("개\n");
        sb.append("5개 일치, 보너스 볼 일치(30000000원)- ").append(results.getOrDefault(-1, 0)).append("개\n");
        sb.append("6개 일치 (2000000000원)- ").append(results.getOrDefault(6, 0)).append("개\n");
        System.out.println(sb.toString());
    }

    public static void profitRate(int rate) {
        System.out.println("총 수익률은 " + rate + "%입니다.");
    }
}
