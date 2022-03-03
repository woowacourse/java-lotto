package lotto.domain.ticket.provider;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import lotto.domain.ticket.Ticket;
import lotto.utils.Rank;

public class TicketsTestProvider {

    public static Stream<Arguments> provideForGenerateTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Ticket(List.of(1, 2, 3, 4, 5, 6))
                        ), 1
                ),
                Arguments.of(
                        List.of(
                                new Ticket(List.of(1, 2, 3, 4, 5, 6)),
                                new Ticket(List.of(11, 12, 13, 14, 15, 16)),
                                new Ticket(List.of(21, 22, 23, 24, 25, 26))
                        ), 3
                )
        );
    }

    public static Stream<Arguments> provideForCalculateRanksTest() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6), 10,
                        List.of(
                                new Ticket(List.of(1, 2, 3, 4, 5, 6))
                        ),
                        List.of(
                                Rank.FIRST_GRADE
                        )
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6), 10,
                        List.of(
                                new Ticket(List.of(1, 2, 3, 4, 5, 6)),
                                new Ticket(List.of(1, 2, 3, 4, 5, 10)),
                                new Ticket(List.of(1, 2, 3, 6, 7, 15)),
                                new Ticket(List.of(11, 12, 13, 14, 15, 16))
                        ),
                        List.of(
                                Rank.FIRST_GRADE,
                                Rank.SECOND_GRADE,
                                Rank.FOURTH_GRADE
                        )
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6), 10,
                        List.of(
                                new Ticket(List.of(1, 2, 3, 4, 5, 6)),
                                new Ticket(List.of(1, 2, 3, 4, 5, 10)),
                                new Ticket(List.of(1, 2, 3, 4, 5, 16)),
                                new Ticket(List.of(1, 2, 3, 4, 15, 16)),
                                new Ticket(List.of(1, 2, 3, 14, 15, 16)),
                                new Ticket(List.of(1, 2, 13, 14, 15, 16)),
                                new Ticket(List.of(11, 12, 13, 14, 15, 16))
                        ),
                        List.of(
                                Rank.FIRST_GRADE,
                                Rank.SECOND_GRADE,
                                Rank.THIRD_GRADE,
                                Rank.FOURTH_GRADE,
                                Rank.FIFTH_GRADE
                        )
                )
        );
    }

}
