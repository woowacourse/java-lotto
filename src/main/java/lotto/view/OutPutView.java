package lotto.view;

import lotto.domain.lotto.LottoTicket;

public class OutPutView {
    private static final String ENTER = "\n";

    public static void showLottoTicket(LottoTicket lottoTicket){
        System.out.println(lottoTicket.getNumberOfLotto() + "를 구매 했습니다.");
        System.out.println(lottoTicket + ENTER);
    }

}
