package lotto.domain.ticket.provider;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import lotto.domain.ticket.Ticket;
import lotto.domain.winning.Rank;
import lotto.dto.TicketDto;

public class TicketsTestProvider {

    private static final List<Integer> DEFAULT_TICKET_NUMBERS = List.of(1, 2, 3, 4, 5, 6);
    private static final TicketDto DEFAULT_TICKET_DTO = new TicketDto(DEFAULT_TICKET_NUMBERS);
    private static final Ticket DEFAULT_TICKET = new Ticket(DEFAULT_TICKET_NUMBERS);


    public static Stream<Arguments> provideForGenerateTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                DEFAULT_TICKET,
                                DEFAULT_TICKET
                        ),
                        List.of(
                                DEFAULT_TICKET_DTO
                        ), 3
                ),
                Arguments.of(
                        List.of(
                                DEFAULT_TICKET,
                                DEFAULT_TICKET
                        ),
                        List.of(
                                DEFAULT_TICKET_DTO,
                                DEFAULT_TICKET_DTO,
                                DEFAULT_TICKET_DTO,
                                DEFAULT_TICKET_DTO
                        ), 6
                ),
                Arguments.of(
                        List.of(
                                DEFAULT_TICKET,
                                DEFAULT_TICKET,
                                DEFAULT_TICKET,
                                DEFAULT_TICKET,
                                DEFAULT_TICKET
                        ),
                        List.of(
                        ), 5
                )
        );
    }

    public static Stream<Arguments> provideForCalculateRanksTest() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6), 10,
                        List.of(
                        ),
                        List.of(
                                new TicketDto(List.of(1, 2, 3, 4, 5, 6)),
                                new TicketDto(List.of(1, 2, 3, 4, 5, 10)),
                                new TicketDto(List.of(1, 2, 3, 6, 7, 15)),
                                new TicketDto(List.of(11, 12, 13, 14, 15, 16))
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
                                new Ticket(List.of(1, 2, 3, 6, 7, 15)),
                                new Ticket(List.of(11, 12, 13, 14, 15, 16))
                        ),
                        List.of(
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
                                new Ticket(List.of(10, 2, 3, 4, 5, 6)),
                                new Ticket(List.of(1, 2, 3, 4, 5, 10))
                        ),
                        List.of(
                                new TicketDto(List.of(1, 2, 3, 4, 7, 8)),
                                new TicketDto(List.of(1, 2, 3, 6, 7, 15)),
                                new TicketDto(List.of(11, 12, 13, 14, 15, 16))
                        ),
                        List.of(
                                Rank.FIRST_GRADE,
                                Rank.SECOND_GRADE,
                                Rank.SECOND_GRADE,
                                Rank.FOURTH_GRADE,
                                Rank.FOURTH_GRADE
                        )
                )
        );
    }

}
