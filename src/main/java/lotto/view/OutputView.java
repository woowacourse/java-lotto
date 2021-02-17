package lotto.view;

import lotto.domain.*;

public class OutputView {
    private OutputView() {
    }

    public static void printLottoTickets(LottoTickets lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets.lottoTickets()) {
            System.out.println(lottoTicket.lottoTicket());
        }
    }

    public static void printResultStatistic(LottoResult lottoResult) {
        for (PrizeType prizeType : PrizeType.values()) {
            resultStatistic(prizeType, lottoResult.getCountByPrizeType(prizeType));
        }
    }

    private static void resultStatistic(PrizeType prizeType, int count) {
        if (prizeType != PrizeType.NO_PRIZE) {
            return;
        }
        if (prizeType == PrizeType.SECOND_PRIZE) {
            System.out.println(prizeType.getMatchCount() + "개 일치," + " 보너스 볼 일치(" + prizeType.getPrizeMoney() + "원)- " + count + "개");
        }
        System.out.println(prizeType.getMatchCount() + "개 일치...(" + prizeType.getPrizeMoney() + ")- " + count + "개");
    }

    public static void printProfitRate(LottoResult lottoResult, Money money) {
        System.out.println("총 수익률은 " + lottoResult.calculateProfitRate(money) + "입니다.");
    }
}