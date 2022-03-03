package lotto;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.analysis.Analysis;
import lotto.domain.money.Money;
import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.TicketManager;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.generator.RandomTicketGenerator;
import lotto.domain.winning.WinningTicket;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.TicketManagerDto;
import lotto.dto.WinningTicketDto;
import lotto.utils.Rank;
import lotto.view.LottoView;

public class LottoApplication {

    public final LottoView lottoView;

    public LottoApplication(final LottoView lottoView) {
        this.lottoView = lottoView;
    }

    public void run() {
        final Money money = insertMoney();
        final TicketManager ticketManager = purchaseTickets(money);
        announceTickets(ticketManager);

        final Analysis analysis = calculateAnalysis(ticketManager, money);
        announceAnalysis(analysis);
    }

    private Money insertMoney() {
        return new Money(lottoView.requestMoney());
    }

    private TicketManager purchaseTickets(final Money money) {
        final int totalTicketCount = money.getQuotient();
        final int manualTicketCount = lottoView.requestManualTicketCount(totalTicketCount);
        final Tickets manualTickets = requestManualTickets(manualTicketCount);
        return TicketManager.generateTickets(totalTicketCount, manualTickets, new RandomTicketGenerator());
    }

    private Tickets requestManualTickets(final int manualTicketCount) {
        final List<TicketDto> manualTicketDtos = lottoView.requestManualTicketDtos(manualTicketCount);
        final List<Ticket> manualTickets = manualTicketDtos.stream()
                .map(TicketDto::toTicket)
                .collect(Collectors.toUnmodifiableList());
        return Tickets.generateTickets(manualTickets);
    }

    private void announceTickets(final TicketManager ticketManager) {
        final TicketManagerDto ticketManagerDto = TicketManagerDto.toDto(ticketManager);
        lottoView.announceTickets(ticketManagerDto);
    }

    private Analysis calculateAnalysis(final TicketManager ticketManager, final Money money) {
        final WinningTicket winningTicket = requestWinningTicket();
        final List<Rank> ranks = ticketManager.calculateRanks(winningTicket);
        return new Analysis(ranks, money);
    }

    private WinningTicket requestWinningTicket() {
        final WinningTicketDto winningTicketDto = lottoView.requestWinningTicketDto();
        return winningTicketDto.toWinningTicket();
    }

    private void announceAnalysis(final Analysis analysis) {
        final AnalysisDto analysisDto = AnalysisDto.toDto(analysis);
        lottoView.announceAnalysis(analysisDto);
    }

}
