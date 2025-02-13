package view;

import controller.dto.LottoRankResponse;
import controller.dto.LottoTicketResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OutputView {

    public void printPurchaseCount(int purchaseCount) {
        System.out.printf("%d개를 구매했습니다.\n", purchaseCount);
    }

    public void printPurchasedLottos(List<LottoTicketResponse> lottoTicketResponses) {
        lottoTicketResponses.forEach(lottoTicketResponse -> {
            Collections.sort(lottoTicketResponse.numbers());
            System.out.println(lottoTicketResponse.numbers());
        });
    }

    public void printLottoRankResults(List<LottoRankResponse> lottoRankResponses) {
        System.out.println("당첨 통계\n" + "---------");
        lottoRankResponses.sort(Comparator.comparing(LottoRankResponse::rankOrder).reversed());
        lottoRankResponses.forEach(lottoRankResponse -> {
            if(lottoRankResponse.isBonusMatched()) {
                System.out.printf(
                        "%d개 일치, 보너스 볼 일치(%d원)- %d개\n",
                        lottoRankResponse.overlappedCount(),
                        lottoRankResponse.winningAmount(),
                        lottoRankResponse.rankMatchCount()
                );
                return;
            }
                    System.out.printf(
                            "%d개 일치 (%d원)- %d개\n",
                            lottoRankResponse.overlappedCount(),
                            lottoRankResponse.winningAmount(),
                            lottoRankResponse.rankMatchCount()
                            );
                }
        );
    }
}
