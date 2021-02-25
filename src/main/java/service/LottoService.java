package service;

import domain.LottoMachine;
import domain.lotto.LottoTicket;
import domain.lotto.LottoTickets;
import domain.lotto.TicketCount;
import view.InputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoService {
    private final LottoMachine lottoMachine;

    public LottoService(final LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public LottoTickets getLottoTickets(final InputView inputView, final TicketCount ticketCount, int manualTicketCount) {
        TicketCount randomTicketCount = ticketCount.reduceTicketCount(manualTicketCount);
        List<LottoTicket> manualLottoTickets = getManualTickets(inputView, manualTicketCount);
        List<LottoTicket> randomLottoTickets = getRandomLottoTickets(randomTicketCount.getTicketCount());
        List<LottoTicket> lottoTickets = Stream.concat(manualLottoTickets.stream(), randomLottoTickets.stream()).collect(Collectors.toList());
        return new LottoTickets(lottoTickets);
    }

    private List<LottoTicket> getRandomLottoTickets(final int randomTicketCount) {
        return lottoMachine.makeRandomTickets(randomTicketCount);
    }

    private List<LottoTicket> getManualTickets(final InputView inputView, final int ticketCount) {
        return lottoMachine.makeManualTickets(inputView, ticketCount);
    }
}
