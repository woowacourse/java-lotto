package lotto.domain;

import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.domain.purchaseamount.PurchaseAmount;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
    public static LottoTicketGroup generateLottos(PurchaseAmount purchaseAmount) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        LottoTicket lottoTicket = LottoTicket.create();

        while (purchaseAmount.buy(lottoTicket)) {
            lottoTickets.add(lottoTicket);
            lottoTicket = LottoTicket.create();
        }

        return new LottoTicketGroup(lottoTickets);
    }

    public static LottoTicketGroup generateLottos(List<String> lottosText, PurchaseAmount purchaseAmount) {
        List<LottoTicket> lottoTickets = lottosText.stream()
                .map(x -> LottoTicket.create(x))
                .collect(Collectors.toList());

        lottoTickets.forEach(x -> purchaseAmount.buy(x));

        return new LottoTicketGroup(lottoTickets);
    }
}
