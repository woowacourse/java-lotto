package view;

import controller.dto.LottoTicketResponse;
import java.util.Collections;
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
}
