package lotto.domain;

import lotto.domain.lotto.LottoStrategy.LottoStrategy;
import lotto.domain.lotto.LottoStrategy.ManualLottoStrategy;
import lotto.domain.lotto.LottoStrategy.RandomLottoStrategy;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
    public static LottoTicketGroup generateLottos(LottoQuantity autoLottoQuantity) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        LottoStrategy strategy = new RandomLottoStrategy();

        while (autoLottoQuantity.biggerThan(lottoTickets.size())) {
            lottoTickets.add(LottoTicket.create(strategy));
        }
        return new LottoTicketGroup(lottoTickets);
    }

    public static LottoTicketGroup generateLottos(List<String> lottosText) {
        List<LottoTicket> lottoTickets = lottosText.stream()
                .map(ManualLottoStrategy::new)
                .map(LottoTicket::create)
                .collect(Collectors.toList())
                ;

        return new LottoTicketGroup(lottoTickets);
    }
}
