package lotto.view;

import lotto.domain.Count;
import lotto.domain.LottoTickets;

import java.sql.SQLOutput;

public class OutputView {
    public static void printInputMoney() {
        System.out.println("구입금액을 입력해주세요.");
    }

    public static void printInputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static void printInputManualLottoTicket() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void printInputWinningLottoTicket() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printInputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printChangeMoney(String changeMoney) {
        System.out.printf("거스름돈은 %s원 입니다.\n", changeMoney);
    }

    public static void printLottoTicketCount(Count manualTicket, Count autoTicket){
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다."
                ,manualTicket.getTicketCount(),autoTicket.getTicketCount());
    }

    public static void printLottoTicket(){
        LottoTickets.getLottoTickets().forEach(lottoTicket -> System.out.println(lottoTicket.getLottoTicket().toString()));
    }
}
