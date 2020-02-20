package lotto.view;

import lotto.domain.Lotto;

import java.util.List;

public class OutputView {
    public static void printPurchaseCount(int lottoTicketCount) {
        System.out.println(lottoTicketCount + "개를 구매했습니다.");
    }

    public static void printLottoNumbers(List<Lotto> lottos) {
        lottos.forEach(lotto ->
                System.out.println(lotto.getLottoNumbers()));
    }
}
