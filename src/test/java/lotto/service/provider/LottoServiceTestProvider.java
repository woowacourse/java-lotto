package lotto.service.provider;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import lotto.domain.rank.Rank;
import lotto.domain.ticket.Ball;
import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.WinningTicket;
import lotto.dto.TicketDto;

public class LottoServiceTestProvider {

    public static Stream<Arguments> provideForGenerateTicketsTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new TicketDto(List.of(1, 2, 3, 4, 5, 6)),
                                new TicketDto(List.of(1, 2, 3, 4, 5, 7)),
                                new TicketDto(List.of(1, 2, 3, 4, 15, 16)),
                                new TicketDto(List.of(1, 2, 3, 14, 15, 16)),
                                new TicketDto(List.of(1, 2, 13, 14, 15, 16))
                        ), 5000
                ),
                Arguments.of(
                        List.of(
                                new TicketDto(List.of(1, 2, 3, 14, 15, 16)),
                                new TicketDto(List.of(1, 2, 13, 14, 15, 16))
                        ), 2000
                ),
                Arguments.of(
                        List.of(
                                new TicketDto(List.of(1, 2, 13, 14, 15, 16))
                        ), 1000
                )
        );
    }

    public static Stream<Arguments> provideForGenerateAnalysisTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new TicketDto(List.of(1, 2, 13, 14, 15, 16))
                        ), 1000,
                        new WinningTicket(new Ticket(List.of(1, 2, 3, 4, 5, 6)), new Ball(7)),
                        Map.of(
                                Rank.FIRST_GRADE, 0,
                                Rank.SECOND_GRADE, 0,
                                Rank.THIRD_GRADE, 0,
                                Rank.FOURTH_GRADE, 0,
                                Rank.FIFTH_GRADE, 0
                        ), "0.00"
                ),
                Arguments.of(
                        List.of(
                                new TicketDto(List.of(1, 2, 3, 4, 5, 6)),
                                new TicketDto(List.of(1, 2, 3, 4, 5, 7)),
                                new TicketDto(List.of(1, 2, 3, 4, 5, 7)),
                                new TicketDto(List.of(1, 2, 3, 14, 15, 16)),
                                new TicketDto(List.of(1, 2, 13, 14, 15, 16))
                        ), 5000,
                        new WinningTicket(new Ticket(List.of(1, 2, 3, 4, 5, 6)), new Ball(7)),
                        Map.of(
                                Rank.FIRST_GRADE, 1,
                                Rank.SECOND_GRADE, 0,
                                Rank.THIRD_GRADE, 2,
                                Rank.FOURTH_GRADE, 0,
                                Rank.FIFTH_GRADE, 1
                        ), "400601000.00"
                ),
                Arguments.of(
                        List.of(
                                new TicketDto(List.of(1, 2, 3, 14, 15, 16)),
                                new TicketDto(List.of(1, 2, 13, 14, 15, 16)),
                                new TicketDto(List.of(10, 11, 12, 15, 16, 18))
                        ), 3000,
                        new WinningTicket(new Ticket(List.of(1, 2, 3, 4, 5, 6)), new Ball(7)),
                        Map.of(
                                Rank.FIRST_GRADE, 0,
                                Rank.SECOND_GRADE, 0,
                                Rank.THIRD_GRADE, 0,
                                Rank.FOURTH_GRADE, 0,
                                Rank.FIFTH_GRADE, 1
                        ), "1666.67"
                )
        );
    }

}
