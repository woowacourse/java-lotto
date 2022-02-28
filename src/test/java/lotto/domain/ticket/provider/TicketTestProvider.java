package lotto.domain.ticket.provider;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import lotto.domain.rank.Rank;

public class TicketTestProvider {

    public static Stream<Arguments> provideForNumbersOutOfSizeExceptionTest() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7, 8))
        );
    }

    public static Stream<Arguments> provideForNumbersDuplicatedExceptionTest() {
        return Stream.of(
                Arguments.of(List.of(1, 1, 3, 4, 5, 6)),
                Arguments.of(List.of(1, 1, 1, 4, 5, 3)),
                Arguments.of(List.of(5, 2, 3, 4, 5, 5))
        );
    }

    public static Stream<Arguments> provideForContainsTrueTest() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 1),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 3),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 6)
        );
    }

    public static Stream<Arguments> provideForContainsFalseTest() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 7),
                Arguments.of(List.of(2, 3, 4, 5, 6, 7), 1)
        );
    }

    public static Stream<Arguments> provideForCountMatchesTest() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(11, 12, 13, 14, 15, 16),
                        0
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 3, 14, 15, 16),
                        3
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 3, 4, 5, 6),
                        6
                )
        );
    }

    public static Stream<Arguments> provideForCalculateRankTest() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(11, 12, 13, 14, 15, 16),
                        17, null
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 3, 14, 15, 16),
                        17, Rank.FIFTH_GRADE
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 3, 4, 5, 16),
                        6, Rank.SECOND_GRADE
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 3, 4, 5, 6),
                        7, Rank.FIRST_GRADE
                )
        );
    }

}
