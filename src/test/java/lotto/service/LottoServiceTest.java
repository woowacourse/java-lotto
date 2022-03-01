package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.AppConfig;
import lotto.domain.ticket.Analysis;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.domain.ticket.WinningTicket;
import lotto.domain.rank.Rank;
import lotto.dto.TicketDto;

class LottoServiceTest {

    private static final AppConfig APP_CONFIG = AppConfig.getInstance();

    private final LottoService lottoService = APP_CONFIG.lottoService;
    private final CustomTicketGenerator customTicketGenerator = APP_CONFIG.ticketGenerator;

    @DisplayName("로또는 구입 금액만큼 생성되어야 한다.")
    @ParameterizedTest(name = "[{index}] {1}원어치 로또 구입")
    @MethodSource("lotto.service.provider.LottoServiceTestProvider#provideForGenerateTicketsTest")
    void generateTicketsTest(final List<TicketDto> expectedTicketDtos, final int money) {
        customTicketGenerator.initTickets(expectedTicketDtos);

        final Tickets tickets = lottoService.generateTickets(money);
        checkTicketEquals(tickets, expectedTicketDtos);
    }

    void checkTicketEquals(final Tickets actual, final List<TicketDto> expected) {
        assertThat(actual.getSize()).isEqualTo(expected.size());
        for (int i = 0; i < actual.getSize(); i++) {
            final List<Integer> actualBallNumbers = actual.getTickets().get(i).getBallNumbers();
            final List<Integer> expectedBallNumbers = expected.get(i).getBallNumbers();
            assertThat(actualBallNumbers).isEqualTo(expectedBallNumbers);
        }
    }

    @DisplayName("당첨 통계의 당첨 등수 개수는 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 당첨 등수 개수 : {3}")
    @MethodSource("lotto.service.provider.LottoServiceTestProvider#provideForGenerateAnalysisTest")
    void generateAnalysisRankCountTest(final List<TicketDto> expectedTicketDtos,
                                       final int money,
                                       final WinningTicket winningTicket,
                                       final Map<Rank, Integer> expectedRankCounts,
                                       final String expectedProfitRate) {
        customTicketGenerator.initTickets(expectedTicketDtos);

        final Tickets tickets = lottoService.generateTickets(money);
        final Analysis analysis = lottoService.generateAnalysis(tickets, winningTicket);
        final Map<Rank, Integer> actualRankCounts = analysis.getRankCounts();
        assertThat(actualRankCounts).isEqualTo(expectedRankCounts);
    }

    @DisplayName("당첨 통계의 수익률은 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 수익률 : {4}")
    @MethodSource("lotto.service.provider.LottoServiceTestProvider#provideForGenerateAnalysisTest")
    void generateAnalysisProfitRateTest(final List<TicketDto> expectedTicketDtos,
                                        final int money,
                                        final WinningTicket winningTicket,
                                        final Map<Rank, Integer> expectedRankCounts,
                                        final String expectedProfitRate) {
        customTicketGenerator.initTickets(expectedTicketDtos);

        final Tickets tickets = lottoService.generateTickets(money);
        final Analysis analysis = lottoService.generateAnalysis(tickets, winningTicket);
        final String actualProfitRate = String.format("%.2f", analysis.getProfitRate());
        assertThat(actualProfitRate).isEqualTo(expectedProfitRate);
    }

}
