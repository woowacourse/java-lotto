package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.rank.Rank;
import lotto.domain.ticket.Ticket;
import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;

class WinningTicketTest {

    @DisplayName("보너스 볼은 당첨 번호와 중복될 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 보너스 볼 : {1}, 당첨 번호 : {0}")
    @MethodSource("provideForBonusNumberDuplicatedExceptionTest")
    void bonusNumberDuplicatedExceptionTest(final List<Integer> winningNumbers, final int bonusNumber) {
        assertThatThrownBy(() -> new WinningTicket(winningNumbers, bonusNumber))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(LottoExceptionStatus.TICKET_NUMBERS_CANNOT_BE_DUPLICATED.getMessage());
    }

    public static Stream<Arguments> provideForBonusNumberDuplicatedExceptionTest() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 1),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 3),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 6)
        );
    }

    @DisplayName("당첨 등수 계산 결과는 기댓값과 일치해야 합니다.")
    @ParameterizedTest(name = "[{index}] 당첨 등수 : {3}")
    @MethodSource("provideForCalculateRankTest")
    void calculateRankTest(final List<Integer> winningNumbers,
                           final int bonusNumber,
                           final Ticket ticket,
                           final Rank expectedRank) {
        final WinningTicket winningTicket = new WinningTicket(winningNumbers, bonusNumber);
        final Rank actualRank = winningTicket.calculateRank(ticket).orElse(null);
        assertThat(actualRank).isEqualTo(expectedRank);
    }

    public static Stream<Arguments> provideForCalculateRankTest() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6), 7,
                        new Ticket(List.of(1, 2, 3, 4, 5, 6)),
                        Rank.FIRST_GRADE
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6), 7,
                        new Ticket(List.of(1, 2, 3, 4, 5, 7)),
                        Rank.SECOND_GRADE
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6), 7,
                        new Ticket(List.of(1, 2, 3, 4, 5, 16)),
                        Rank.THIRD_GRADE
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6), 7,
                        new Ticket(List.of(1, 2, 3, 4, 15, 16)),
                        Rank.FOURTH_GRADE
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6), 7,
                        new Ticket(List.of(1, 2, 3, 14, 15, 16)),
                        Rank.FIFTH_GRADE
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6), 7,
                        new Ticket(List.of(1, 2, 13, 14, 15, 16)),
                        null
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6), 7,
                        new Ticket(List.of(11, 12, 13, 14, 15, 16)),
                        null
                )
        );
    }

}
