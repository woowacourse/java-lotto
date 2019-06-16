package lotto.view;

import lotto.domain.vo.LottoResult_VO;
import lotto.domain.LottoTicket;

public class ConsoleOutPutView {
    public static final String NEW_LINE = "\n";

    public static void showLottoTicket(LottoTicket lottoTicket) {
        System.out.println(NEW_LINE + "수동으로 "
                + lottoTicket.getNumberOfCustomLotto()
                + "장, 자동으로 "
                + lottoTicket.getNumberOfAutoLotto()
                + "장을 구매 했습니다.");
        System.out.println(lottoTicket + NEW_LINE);
    }

    public static void showLottoResult(LottoResult_VO lottoResult) {
        System.out.println(NEW_LINE + "당첨 통계");
        System.out.println("--------------------");

        lottoResult.getRank()
                .stream().forEach(System.out::print);

        System.out.println(NEW_LINE + "총 수익률은 "
                + lottoResult.dividendRate()
                + "% 입니다.");
    }
}
