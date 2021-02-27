package service;

import domain.LottoMachine;
import domain.lotto.LottoTicket;
import domain.lotto.LottoTickets;
import domain.lotto.TicketCount;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoService {
    private final LottoMachine lottoMachine;

    public LottoService(final LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }


    public LottoTickets makeLottoTickets(final List<List<Integer>> manualTicketsNumbers, final TicketCount randomTicketCount) {
        List<LottoTicket> manualLottoTickets = getManualTickets(manualTicketsNumbers);
        List<LottoTicket> randomLottoTickets = getRandomLottoTickets(randomTicketCount);
        List<LottoTicket> lottoTickets = Stream.concat(manualLottoTickets.stream(), randomLottoTickets.stream()).collect(Collectors.toList());
        return new LottoTickets(lottoTickets);
    }

    private List<LottoTicket> getRandomLottoTickets(final TicketCount randomTicketCount) {
        return lottoMachine.makeRandomTickets(randomTicketCount.getTicketCount());
    }

    private List<LottoTicket> getManualTickets(final List<List<Integer>> manualTicketsNumbers) {
        return lottoMachine.makeManualTickets(manualTicketsNumbers);
    }
}
