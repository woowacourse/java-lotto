package view;

import controller.dto.LottoRankResponse;
import controller.dto.LottoTicketResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OutputView {

    public void printPurchasedTicketAmount(int purchasedTicketAmount) {
        System.out.printf("%d개를 구매했습니다.\n", purchasedTicketAmount);
    }

    public void printPurchasedLottos(List<LottoTicketResponse> lottoTicketResponses) {
        lottoTicketResponses.forEach(lottoTicketResponse -> {
            Collections.sort(lottoTicketResponse.numbers());
            System.out.println(lottoTicketResponse.numbers());
        });
    }

    public void printLottoRankResults(List<LottoRankResponse> lottoRankResponses) {
        System.out.println("당첨 통계\n" + "---------");

        lottoRankResponses.sort(Comparator.comparing(LottoRankResponse::prizeMoney));

        lottoRankResponses.forEach(lottoRankResponse -> {
                    if (lottoRankResponse.isBonusMatched()) {
                        printLottoRankResultWithBonusNumber(lottoRankResponse);
                        return;
                    }
                    printLottoRankResultWithoutBonusNumber(lottoRankResponse);
                }
        );
    }

    public void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.2f입니다.", Math.floor(profitRate * 100) / 100);
    }

    private void printLottoRankResultWithoutBonusNumber(LottoRankResponse lottoRankResponse) {
        System.out.printf(
                "%d개 일치 (%d원)- %d개\n",
                lottoRankResponse.overlappedCount(),
                lottoRankResponse.prizeMoney(),
                lottoRankResponse.rankMatchCount()
        );
    }

    private void printLottoRankResultWithBonusNumber(LottoRankResponse lottoRankResponse) {
        System.out.printf(
                "%d개 일치, 보너스 볼 일치(%d원)- %d개\n",
                lottoRankResponse.overlappedCount(),
                lottoRankResponse.prizeMoney(),
                lottoRankResponse.rankMatchCount()
        );
    }

}
