package lotto.controller;

import java.util.Scanner;
import lotto.domain.LottoMachine;
import lotto.domain.LottoTickets;

public class LottoController {

    public void run() {
        Scanner scanner = new Scanner(System.in);

        LottoMachine lottoMachine = new LottoMachine();
        System.out.println("구입금액을 입력해 주세요.");
        LottoTickets lottoTickets = lottoMachine.purchase(scanner.nextLine());
        System.out.println(lottoTickets.totalCount() + "개를 구매했습니다.");
        System.out.println(lottoTickets.getTicketsInfo());

        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

    }
}
