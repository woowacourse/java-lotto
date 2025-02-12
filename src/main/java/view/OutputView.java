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
    }

    public static void printStatistics(EnumMap<Prize, Integer> prizeMap) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Prize prize : prizeMap.keySet()) {
            
        }
    }
}
