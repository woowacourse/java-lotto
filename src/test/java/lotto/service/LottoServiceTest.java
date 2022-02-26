package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import lotto.dto.TicketsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.dto.TicketDto;

class LottoServiceTest {

    private final CustomTicketGenerator customTicketGenerator = new CustomTicketGenerator();
    private final LottoService lottoService = new LottoService(customTicketGenerator);

    @DisplayName("로또 생성 확인 테스트")
    @ParameterizedTest
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

}
