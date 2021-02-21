package view;

import domain.lotto.TicketCount;
import util.OutputUtil;

public class MainScreen {
    public static final String BUY_STATUS = "%d개를 구매했습니다.";

    public void showInputMoney() {
        OutputUtil.printMessage("구입금액을 입력해 주세요.");
    }

    public void showTicketCount(final TicketCount lottoCount) {
        OutputUtil.printMessage(String.format(BUY_STATUS, lottoCount.getTicketCount()));
    }
}
