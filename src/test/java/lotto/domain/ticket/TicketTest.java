package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import lotto.domain.rank.Rank;
import lotto.exception.LottoException;
import lotto.exception.ticket.TicketNumbersExceptionStatus;

class TicketTest {

    @DisplayName("로또 번호 묶음은 NULL이 아니어야 합니다.")
    @ParameterizedTest
    @NullSource
    void ticketNullExceptionTest(final List<Integer> numbers) {
        assertThatThrownBy(() -> new Ticket(numbers))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(TicketNumbersExceptionStatus.NUMBERS_IS_NULL.getMessage());
    }

    @DisplayName("로또 번호 묶음은 6개로 구성되여야 합니다.")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}")
    @MethodSource("lotto.domain.ticket.provider.TicketTestProvider#provideForNumbersOutOfSizeExceptionTest")
    void ticketOutOfSizeExceptionTest(final List<Integer> numbers) {
        assertThatThrownBy(() -> new Ticket(numbers))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(TicketNumbersExceptionStatus.NUMBERS_OUT_OF_SIZE.getMessage());
    }

    @DisplayName("로또 번호 묶음 중 중복 값은 존재할 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}")
    @MethodSource("lotto.domain.ticket.provider.TicketTestProvider#provideForNumbersDuplicatedExceptionTest")
    void ticketNumbersDuplicatedExceptionTest(final List<Integer> numbers) {
        assertThatThrownBy(() -> new Ticket(numbers))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(TicketNumbersExceptionStatus.NUMBERS_DUPLICATED.getMessage());
    }

    @DisplayName("특정 번호 포함 여부 확인 테스트")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}, 특정 번호 : {1}")
    @MethodSource("lotto.domain.ticket.provider.TicketTestProvider#provideForContainsTest")
    void containsTest(final List<Integer> numbers, final int targetNumber) {
        final Ticket ticket = new Ticket(numbers);
        final Ball targetBall = new Ball(targetNumber);

        assertThat(ticket.contains(targetBall)).isTrue();
    }

    @DisplayName("당첨 번호와 일치 개수 확인 테스트")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}, 일치 개수 : {2}, 당첨 번호 : {1}")
    @MethodSource("lotto.domain.ticket.provider.TicketTestProvider#provideForCountMatchesTest")
    void countMatches(final List<Integer> numbers, final List<Integer> winningNumbers, final int matchCount) {
        final Ticket ticket = new Ticket((numbers));
        final Ticket winningTicket = new Ticket(winningNumbers);
        final List<Integer> winningBallNumbers = winningTicket.getBallNumbers();
        final List<Ball> winningBalls = winningBallNumbers.stream()
                .map(Ball::new)
                .collect(Collectors.toUnmodifiableList());

        assertThat(ticket.countMatches(winningBalls)).isEqualTo(matchCount);
    }

    @DisplayName("당첨 등수 확인 테스트")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}, 당첨 번호 : {1}, 보너스 번호 : {2}, 당첨 등수 : {3}")
    @MethodSource("lotto.domain.ticket.provider.TicketTestProvider#provideForCalculateRankTest")
    void calculateRankTest(final List<Integer> numbers,
                           final List<Integer> winningNumbers,
                           final int bonusNumber,
                           final Rank expected) {
        final Ticket ticket = new Ticket((numbers));
        final Ticket winningTicket = new Ticket(winningNumbers);
        final Ball bonusBall = new Ball(bonusNumber);
        final Rank rank = ticket.calculateRank(winningTicket, bonusBall).orElse(null);
        assertThat(rank).isEqualTo(expected);
    }

}
