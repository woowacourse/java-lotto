package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.domain.rank.Rank;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.TicketsDto;
import lotto.dto.WinningTicketDto;

class LottoServiceTest {

    private final CustomTicketGenerator customTicketGenerator = new CustomTicketGenerator();
    private final LottoService lottoService = new LottoService(customTicketGenerator);

    @DisplayName("로또 생성 확인 테스트")
    @ParameterizedTest(name = "[{index}] {1}원어치 로또 구입")
    @MethodSource("provideForGenerateTicketsTest")
    void generateTicketsTest(final List<TicketDto> expectedTicketDtos, final int money) {
        customTicketGenerator.initNumbers(expectedTicketDtos);
        lottoService.generateTickets(money);

        final TicketsDto ticketsDto = lottoService.getTicketDtos();
        final List<TicketDto> actualTicketDtos = ticketsDto.getTicketDtos();

        checkTicketEquals(actualTicketDtos, expectedTicketDtos);
    }

    public static Stream<Arguments> provideForGenerateTicketsTest() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new TicketDto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                                new TicketDto(Arrays.asList(1, 2, 3, 4, 5, 7)),
                                new TicketDto(Arrays.asList(1, 2, 3, 4, 15, 16)),
                                new TicketDto(Arrays.asList(1, 2, 3, 14, 15, 16)),
                                new TicketDto(Arrays.asList(1, 2, 13, 14, 15, 16))
                        ), 5000
                )
        );
    }

    void checkTicketEquals(final List<TicketDto> actual, final List<TicketDto> expected) {
        assertThat(actual.size()).isEqualTo(expected.size());
        for (int i = 0; i < actual.size(); i++) {
            assertThat(actual.get(i).getBallNumbers()).isEqualTo(expected.get(i).getBallNumbers());
        }
    }


    @DisplayName("당첨 통계, 당첨 등수 개수 확인 테스트")
    @ParameterizedTest(name = "[{index}] 당첨 등수 개수 : {3}")
    @MethodSource("provideForGenerateAnalysisTest")
    void generateAnalysisRankCountTest(final List<TicketDto> expectedTicketDtos,
                                       final int money,
                                       final WinningTicketDto winningTicketDto,
                                       final Map<Rank, Integer> expectedRankCounts,
                                       final String expectedProfitRate) {
        customTicketGenerator.initNumbers(expectedTicketDtos);
        lottoService.generateTickets(money);

        final AnalysisDto analysisDto = lottoService.generateAnalysis(winningTicketDto);
        final Map<Rank, Integer> actualRankCounts = analysisDto.getRankCounts();
        assertThat(actualRankCounts).isEqualTo(expectedRankCounts);
    }

    @DisplayName("당첨 통계, 수익률 확인 테스트")
    @ParameterizedTest(name = "[{index}] 수익률 : {4}")
    @MethodSource("provideForGenerateAnalysisTest")
    void generateAnalysisProfitRateTest(final List<TicketDto> expectedTicketDtos,
                                       final int money,
                                       final WinningTicketDto winningTicketDto,
                                       final Map<Rank, Integer> expectedRankCounts,
                                       final String expectedProfitRate) {
        customTicketGenerator.initNumbers(expectedTicketDtos);
        lottoService.generateTickets(money);

        final AnalysisDto analysisDto = lottoService.generateAnalysis(winningTicketDto);
        final String actualProfitRate = String.format("%.2f", analysisDto.getProfitRate());
        assertThat(actualProfitRate).isEqualTo(expectedProfitRate);
    }

    public static Stream<Arguments> provideForGenerateAnalysisTest() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new TicketDto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                                new TicketDto(Arrays.asList(1, 2, 3, 4, 5, 7)),
                                new TicketDto(Arrays.asList(1, 2, 3, 4, 5, 7)),
                                new TicketDto(Arrays.asList(1, 2, 3, 14, 15, 16)),
                                new TicketDto(Arrays.asList(1, 2, 13, 14, 15, 16))
                        ), 5000,
                        new WinningTicketDto(Arrays.asList(1,2,3,4,5,6), 7),
                        Map.of(
                                Rank.FIRST_GRADE, 1,
                                Rank.SECOND_GRADE, 2,
                                Rank.THIRD_GRADE, 0,
                                Rank.FOURTH_GRADE, 0,
                                Rank.FIFTH_GRADE, 1
                        ), "412001000.00"
                )
        );
    }

}
