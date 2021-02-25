package lotto.service;

import lotto.domain.lottos.LottoTicket;
import lotto.domain.lottos.LottoTickets;
import lotto.domain.lottos.amount.LottoAmount;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTicketsService {

    private LottoTicketsService() {
    }

    public static LottoTickets createLottoTickets(LottoAmount lottoAmount, List<String> inputLottosManualNumber) {
        List<LottoTicket> lottoTicketGroup = createManualLottoTickets(inputLottosManualNumber);
        lottoTicketGroup.addAll(createAutoLottoTickets(lottoAmount.getAutoAmount()));
        return new LottoTickets(lottoTicketGroup);
    }

    public static List<LottoTicket> createAutoLottoTickets(int autoAmount) {
        return Stream.generate(LottoTicketService::createAutoLottoTicket)
                .limit(autoAmount)
                .collect(Collectors.toList());
    }

    public static List<LottoTicket> createManualLottoTickets(List<String> inputLottosManualNumber) {
        return inputLottosManualNumber.stream()
                .map(LottoTicketService::createManualLottoTicket)
                .collect(Collectors.toList());
    }
}
