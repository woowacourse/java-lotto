package lotto.domain.ticket.provider;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import lotto.domain.rank.Rank;
import lotto.dto.TicketDto;

public class TicketsTestProvider {

    public static Stream<Arguments> provideForGenerateTest() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new TicketDto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                                new TicketDto(Arrays.asList(1, 2, 3, 4, 5, 10)),
                                new TicketDto(Arrays.asList(1, 2, 3, 6, 7, 15)),
                                new TicketDto(Arrays.asList(11, 12, 13, 14, 15, 16))
                        ), 4
                )
        );
    }

    public static Stream<Arguments> provideForCalculateRanksTest() {
        return Stream.of(
                Arguments.of(
                        new TicketDto(Arrays.asList(1, 2, 3, 4, 5, 6)), 10,
                        Arrays.asList(
                                new TicketDto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                                new TicketDto(Arrays.asList(1, 2, 3, 4, 5, 10)),
                                new TicketDto(Arrays.asList(1, 2, 3, 6, 7, 15)),
                                new TicketDto(Arrays.asList(11, 12, 13, 14, 15, 16))
                        ), 4,
                        Arrays.asList(
                                Rank.FIRST_GRADE,
                                Rank.SECOND_GRADE,
                                Rank.FOURTH_GRADE
                        )
                )
        );
    }

}
