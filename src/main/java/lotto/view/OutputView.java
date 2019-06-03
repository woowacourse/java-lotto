package lotto.view;

import lotto.domain.ticket.LottoTickets;

public class OutputView {
    static public void  printInsertedMoneyInformation(int count, int rest){
        System.out.println("구매 로또 수: " + count);
        System.out.println("잔돈: " + rest + "원");
    }

    public static void printLottoTicketsInformaion(final LottoTickets lottoTickets) {
        System.out.println("\n번호 리스트");
        System.out.println(lottoTickets.toString());
    }
}
