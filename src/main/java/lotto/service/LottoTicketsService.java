package lotto.service;

import lotto.domain.lottos.LottoTicket;
import lotto.domain.lottos.LottoTickets;
import lotto.domain.money.Money;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTicketsService {

    private LottoTicketsService() {
    }

    public static LottoTickets createLottoTickets(Money money, List<String> inputLottosManualNumber) {
        List<LottoTicket> lottoTicketGroup = createManualLottoTickets(inputLottosManualNumber);
        lottoTicketGroup.addAll(createAutoLottoTickets(money.getLottoCount() - inputLottosManualNumber.size()));
        return new LottoTickets(lottoTicketGroup);
    }

    public static List<LottoTicket> createAutoLottoTickets(int autoCount) {
        return Stream.generate(LottoTicketService::createAutoLottoTicket)
                .limit(autoCount)
                .collect(Collectors.toList());
    }

    public static List<LottoTicket> createManualLottoTickets(List<String> inputLottosManualNumber) {
        return inputLottosManualNumber.stream()
                .map(LottoTicketService::createManualLottoTicket)
                .collect(Collectors.toList());
    }
}
