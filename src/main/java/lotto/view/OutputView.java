package lotto.view;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static void printInputMoney() {
        System.out.println("구입금액을 입력해주세요.");
    }

    public static void printInputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static void printInputManualLottoTicket(int manualLottoTicketCount) {
        if (manualLottoTicketCount > 0) {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        }
    }


    public static void printInputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printChangeMoney(String changeMoney) {
        System.out.printf("거스름돈은 %s원 입니다.\n", changeMoney);
    }

    public static void printLottoTicketCount(LottoTicketCount manualTicket, LottoTicketCount autoTicket) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n"
                , manualTicket.getTicketCount(), autoTicket.getTicketCount());
    }

    public static void printLottoTicket() {
        List<LottoTicket> lottoTickets = LottoTickets.getLottoTickets();

        lottoTickets.forEach(lottoTicket ->
                System.out.println(lottoTicket.getLottoTicket()
                        .stream()
                        .map(LottoBall::getLottoBall)
                        .collect(Collectors.toList())));
    }

    public static void printWinningTicket() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printEachRankCount(Map<Rank, Long> eachRankCount) {
        System.out.println("당첨통계");
        System.out.println("-------");
        List<Rank> rank = new ArrayList<>(Arrays.asList(Rank.values()));
        rank.remove(Rank.NO_RANK);

        for(Rank r : rank){
            System.out.printf("%d개 일치 ",r.getWinningCount());
            printHitBonusBall(r);
            System.out.printf("(%d원) - %d개\n",(long)r.getWinningMoney(),eachRankCount.get(r));
        }
    }

    private static void printHitBonusBall(Rank r) {
        if (r == Rank.SECOND){
            System.out.print("보너스 볼 일치");
        }
    }

    public static void printEarningRate(double earningRate){
        System.out.printf("총 수익울은 %.1f%% 입니다.", earningRate);
    }
}
