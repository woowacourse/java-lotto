package lotto.domain.Factory;

import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.view.InputView;

public class LottoTicketsFactory {
    private static LottoTickets getLottoTickets(Money money, int amountOfCustoms) {
        LottoTickets lottoTickets = new LottoTickets(amountOfCustoms);

        InputView.printCustomLottoNumbersMessage();
        try {
            purchaseCustomLottoTickets(lottoTickets);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            purchaseCustomLottoTickets(lottoTickets);
        }
        purchaseAutoLottoTickets(money, amountOfCustoms, lottoTickets);

        return lottoTickets;
    }

    private static void purchaseAutoLottoTickets(Money money, int amountOfCustoms, LottoTickets lottoTickets) {
        int amountOfAutos = money.getTicketCount() - amountOfCustoms;
        for (int i = 0; i < amountOfAutos; i++) {
            lottoTickets.putLottoTicket(null);
        }
    }

    private static void purchaseCustomLottoTickets(LottoTickets lottoTickets) {
        while (lottoTickets.needMoreCustomLottoTicket()) {
            lottoTickets.putLottoTicket(InputView.inputLottoNumbers());
        }
    }
}
