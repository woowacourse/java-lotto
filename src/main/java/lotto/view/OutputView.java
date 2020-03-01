package lotto.view;

import lotto.domain.LottoTicketCount;
import lotto.domain.LottoBall;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printInputMoney() {
        System.out.println("구입금액을 입력해주세요.");
    }

    public static void printInputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static void printInputManualLottoTicket(int manualLottoTicketCount) {
        if(manualLottoTicketCount > 0) {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        }
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

    public static void printLottoTicketCount(LottoTicketCount manualTicket, LottoTicketCount autoTicket){
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n"
                ,manualTicket.getTicketCount(),autoTicket.getTicketCount());
    }

    public static void printLottoTicket(){
        List<LottoTicket> lottoTickets = LottoTickets.getLottoTickets();

        lottoTickets.forEach(lottoTicket ->
                System.out.println(lottoTicket.getLottoTicket()
                        .stream()
                        .map(LottoBall::getLottoBall)
                        .collect(Collectors.toList())));
    }

    public static void printWinningTicket(){
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }
}
