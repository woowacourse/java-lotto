package lotto.controller.provider;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import lotto.dto.TicketDto;

public class LottoControllerTestProvider {

    public static Stream<Arguments> provideForPurchaseTicketsTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new TicketDto(List.of(1, 2, 3, 4, 5, 6))
                        ), "1000"
                ),
                Arguments.of(
                        List.of(
                                new TicketDto(List.of(1, 2, 3, 4, 5, 6)),
                                new TicketDto(List.of(11, 12, 13, 14, 15, 16)),
                                new TicketDto(List.of(21, 22, 23, 24, 25, 26))
                        ), "3000"
                ),
                Arguments.of(
                        List.of(
                                new TicketDto(List.of(1, 2, 3, 4, 5, 6)),
                                new TicketDto(List.of(11, 12, 13, 14, 15, 16)),
                                new TicketDto(List.of(21, 22, 23, 24, 25, 26)),
                                new TicketDto(List.of(31, 32, 33, 34, 35, 36)),
                                new TicketDto(List.of(40, 41, 42, 43, 44, 45))
                        ), "5000"
                )
        );
    }

    public static Stream<Arguments> provideForWinningNumbersNotNumericExceptionTest() {
        final String moneyText = "1000";
        final List<TicketDto> ticketDtos = List.of(new TicketDto(List.of(1, 2, 3, 4, 5, 6)));
        return Stream.of(
                Arguments.of(moneyText, "", "45", ticketDtos),
                Arguments.of(moneyText, "1a", "45", ticketDtos),
                Arguments.of(moneyText, "11, a", "45", ticketDtos)
        );
    }

    public static Stream<Arguments> provideForWinningNumbersOutOfSizeExceptionTest() {
        final String moneyText = "1000";
        final List<TicketDto> ticketDtos = List.of(new TicketDto(List.of(1, 2, 3, 4, 5, 6)));
        return Stream.of(
                Arguments.of(moneyText, "1,2", "45", ticketDtos),
                Arguments.of(moneyText, "1,2,3,4,5", "45", ticketDtos),
                Arguments.of(moneyText, "1,2,3,4,5,6,7", "45", ticketDtos)
        );
    }

    public static Stream<Arguments> provideForWinningNumbersOutOfRangeExceptionTest() {
        final String moneyText = "1000";
        final List<TicketDto> ticketDtos = List.of(new TicketDto(List.of(1, 2, 3, 4, 5, 6)));
        return Stream.of(
                Arguments.of(moneyText, "0, 1, 2, 3, 4, 5", "45", ticketDtos),
                Arguments.of(moneyText, "1, 2, 3, 4, 5, 46", "45", ticketDtos),
                Arguments.of(moneyText, "-1, 1, 2, 3, 4, 100", "45", ticketDtos)
        );
    }

    public static Stream<Arguments> provideForWinningNumbersDuplicatedExceptionTest() {
        final String moneyText = "1000";
        final List<TicketDto> ticketDtos = List.of(new TicketDto(List.of(1, 2, 3, 4, 5, 6)));
        return Stream.of(
                Arguments.of(moneyText, "1, 2, 3, 4, 5, 5", "45", ticketDtos),
                Arguments.of(moneyText, "1, 1, 3, 4, 5, 6", "45", ticketDtos)
        );
    }

    public static Stream<Arguments> provideForBonusNumberNotNumericExceptionTest() {
        final String moneyText = "1000";
        final List<TicketDto> ticketDtos = List.of(new TicketDto(List.of(1, 2, 3, 4, 5, 6)));
        return Stream.of(
                Arguments.of(moneyText, "1, 2, 3, 4, 5, 6", "", ticketDtos),
                Arguments.of(moneyText, "1, 2, 3, 4, 5, 6", "1-23", ticketDtos),
                Arguments.of(moneyText, "1, 1, 3, 4, 5, 6", "1a", ticketDtos)
        );
    }

    public static Stream<Arguments> provideForBonusNumberOutOfRangeExceptionTest() {
        final String moneyText = "1000";
        final List<TicketDto> ticketDtos = List.of(new TicketDto(List.of(1, 2, 3, 4, 5, 6)));
        return Stream.of(
                Arguments.of(moneyText, "1, 2, 3, 4, 5, 6", "-1", ticketDtos),
                Arguments.of(moneyText, "1, 2, 3, 4, 5, 6", "0", ticketDtos),
                Arguments.of(moneyText, "1, 2, 3, 4, 5, 6", "46", ticketDtos)
        );
    }

    public static Stream<Arguments> provideForBonusNumberDuplicatedExceptionTest() {
        final String moneyText = "1000";
        final List<TicketDto> ticketDtos = List.of(new TicketDto(List.of(1, 2, 3, 4, 5, 6)));
        return Stream.of(
                Arguments.of(moneyText, "1, 2, 3, 4, 5, 6", "1", ticketDtos),
                Arguments.of(moneyText, "1, 2, 3, 4, 5, 6", "6", ticketDtos)
        );
    }


}
