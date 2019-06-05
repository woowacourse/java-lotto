package lotto.domain.lottoticket.ticketingmachine;

import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberPool;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketingMachine {
    public static LottoTickets generateLottoTickets(long numOfTickets, LottoNumberGenerator generator) {
        List<LottoTicket> tickets = new ArrayList<>();
        for (int i = 0; i < numOfTickets; i++) {
            LottoTicket lottoTicket = generateLottoTicket(generator.create());
            tickets.add(lottoTicket);
        }
        return new LottoTickets(tickets);
    }

    private static LottoTicket generateLottoTicket(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumberPool::valueOf)
                .collect(Collectors.toList());

        return new LottoTicket(lottoNumbers);
    }
}
