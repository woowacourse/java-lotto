package lotto.domain.ticket.provider;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import lotto.domain.rank.Rank;
import lotto.dto.TicketDto;

public class TicketsTestProvider {

    public static Stream<Arguments> provideForGenerateTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new TicketDto(List.of(1, 2, 3, 4, 5, 6)),
                                new TicketDto(List.of(1, 2, 3, 4, 5, 10)),
                                new TicketDto(List.of(1, 2, 3, 6, 7, 15)),
                                new TicketDto(List.of(11, 12, 13, 14, 15, 16))
                        ), 4
                )
        );
    }

    public static Stream<Arguments> provideForCalculateRanksTest() {
        return Stream.of(
                Arguments.of(
                        new TicketDto(List.of(1, 2, 3, 4, 5, 6)), 10,
                        List.of(
                                new TicketDto(List.of(1, 2, 3, 4, 5, 6)),
                                new TicketDto(List.of(1, 2, 3, 4, 5, 10)),
                                new TicketDto(List.of(1, 2, 3, 6, 7, 15)),
                                new TicketDto(List.of(11, 12, 13, 14, 15, 16))
                        ), 4,
                        List.of(
                                Rank.FIRST_GRADE,
                                Rank.SECOND_GRADE,
                                Rank.FOURTH_GRADE
                        )
                )
        );
    }

}
