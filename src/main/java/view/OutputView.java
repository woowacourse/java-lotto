package view;

import java.util.EnumMap;
import java.util.List;
import model.Lotto;
import model.Prize;
import service.LottoFactory;

public class OutputView {

    public static void printLottoCount(final LottoFactory lottoFactory) {
        System.out.println(String.format("%d개 구매했습니다.", lottoFactory.getTicketNumber()));
    }

    public static void printLottoTickets(final LottoFactory lottoFactory) {
        List<Lotto> lottoList = lottoFactory.getIssuedTickets();
        for (int i = 0; i < lottoList.size(); i++) {
            System.out.println(lottoList.get(i).getNumbers().toString());
        }
        System.out.println();
    }

    public static void printStatistics(EnumMap<Prize, Integer> prizeMap) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Prize prize : prizeMap.keySet()) {
            System.out.println(prize.getComment() + " - " + prizeMap.get(prize) + "개");
        }
    }

    public static void printBenefit(double benefit) {
        String result = "";
        if (benefit >= 1) {
            result = "이익";
        } else {
            result = "손해";
        }
        System.out.println(String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)", benefit, result));
    }
}
