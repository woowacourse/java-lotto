package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.AppConfig;
import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.domain.rank.Rank;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.WinningTicketDto;

class LottoServiceTest {

    private static final AppConfig APP_CONFIG = AppConfig.getInstance();

    private final LottoService lottoService = APP_CONFIG.lottoService;
    private final CustomTicketGenerator customTicketGenerator = APP_CONFIG.ticketGenerator;

    @DisplayName("로또는 구입 금액만큼 생성되어야 한다.")
    @ParameterizedTest(name = "[{index}] {1}원어치 로또 구입")
    @MethodSource("lotto.service.provider.LottoServiceTestProvider#provideForGenerateTicketsTest")
    void generateTicketsTest(final List<TicketDto> expectedTicketDtos, final int money) {
        customTicketGenerator.initTickets(expectedTicketDtos);
        lottoService.generateTickets(money);

        final List<TicketDto> actualTicketDtos = lottoService.getTicketDtos();
        checkTicketEquals(actualTicketDtos, expectedTicketDtos);
    }

    void checkTicketEquals(final List<TicketDto> actual, final List<TicketDto> expected) {
        assertThat(actual.size()).isEqualTo(expected.size());
        for (int i = 0; i < actual.size(); i++) {
            assertThat(actual.get(i).getBallNumbers()).isEqualTo(expected.get(i).getBallNumbers());
        }
    }

    @DisplayName("당첨 통계의 당첨 등수 개수는 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 당첨 등수 개수 : {3}")
    @MethodSource("lotto.service.provider.LottoServiceTestProvider#provideForGenerateAnalysisTest")
    void generateAnalysisRankCountTest(final List<TicketDto> expectedTicketDtos,
                                       final int money,
                                       final WinningTicketDto winningTicketDto,
                                       final Map<Rank, Integer> expectedRankCounts,
                                       final String expectedProfitRate) {
        customTicketGenerator.initTickets(expectedTicketDtos);
        lottoService.generateTickets(money);

        final AnalysisDto analysisDto = lottoService.generateAnalysis(winningTicketDto);
        final Map<Rank, Integer> actualRankCounts = analysisDto.getRankCounts();
        assertThat(actualRankCounts).isEqualTo(expectedRankCounts);
    }

    @DisplayName("당첨 통계의 수익률은 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 수익률 : {4}")
    @MethodSource("lotto.service.provider.LottoServiceTestProvider#provideForGenerateAnalysisTest")
    void generateAnalysisProfitRateTest(final List<TicketDto> expectedTicketDtos,
                                        final int money,
                                        final WinningTicketDto winningTicketDto,
                                        final Map<Rank, Integer> expectedRankCounts,
                                        final String expectedProfitRate) {
        customTicketGenerator.initTickets(expectedTicketDtos);
        lottoService.generateTickets(money);

        final AnalysisDto analysisDto = lottoService.generateAnalysis(winningTicketDto);
        final String actualProfitRate = String.format("%.2f", analysisDto.getProfitRate());
        assertThat(actualProfitRate).isEqualTo(expectedProfitRate);
    }

}
