package lottogame.view;

import lottogame.domain.LottoResult;
import lottogame.domain.LottoTickets;
import lottogame.domain.Money;
import lottogame.domain.Rank;

public class OutputView {
    public static void printPurchaseResult(LottoTickets lottoTickets, int numberOfManualTicket) {
        System.out.println("수동으로 " + numberOfManualTicket + "개, 자동으로 " +
                getNumberOfAutoTicket(lottoTickets, numberOfManualTicket) + "개를 구매했습니다.");
        System.out.println(lottoTickets);
    }

    private static int getNumberOfAutoTicket(LottoTickets lottoTickets, int numberOfManualTicket) {
        return lottoTickets.numberOfLottos() - numberOfManualTicket;
    }

    public static void printRequestOfManualLottoNumber() {
        System.out.println("수동으로 구매할 로또 번호를 입력해 주세요.");
    }

    public static void printLottoResult(LottoResult lottoResult, long rateOfLotto) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (Rank rank : Rank.values()) {
            printMatchResultOf(rank, lottoResult);
        }
        System.out.println("총 수익률은 " + rateOfLotto + "%입니다.");
    }

    private static void printMatchResultOf(Rank rank, LottoResult lottoResult) {
        if (rank == Rank.MISS) {
            return;
        }
        if (rank == Rank.SECOND) {
            System.out.println(rank.getNumberOfMatch() + "개 일치, 보너스 볼 일치(" + rank.getPrize() + "원) - " + lottoResult.getNumberOfMatch(rank) + "개");
            return;
        }
        System.out.println(rank.getNumberOfMatch() + "개 일치 (" + rank.getPrize() + "원) - " + lottoResult.getNumberOfMatch(rank) + "개");
    }
}
