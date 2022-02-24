package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lotto.domain.rank.Rank;
import lotto.exception.LottoException;
import lotto.exception.ticket.TicketNumbersExceptionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

class TicketTest {

	@DisplayName("숫자 요소는 NULL이 아니어야 합니다.")
	@ParameterizedTest
	@NullSource
	void ticketNullExceptionTest(final List<Integer> numbers) {
		assertThatThrownBy(() -> new Ticket(numbers))
				.isInstanceOf(LottoException.class)
				.hasMessageContaining(TicketNumbersExceptionStatus.NUMBERS_IS_NULL.getMessage());
	}

	@DisplayName("숫자 요소는 6개여야 합니다.")
	@ParameterizedTest
	@MethodSource("provideForNumbersOutOfSizeExceptionTest")
	void ticketOutOfSizeExceptionTest(final List<Integer> numbers) {
		assertThatThrownBy(() -> new Ticket(numbers))
				.isInstanceOf(LottoException.class)
				.hasMessageContaining(TicketNumbersExceptionStatus.NUMBERS_OUT_OF_SIZE.getMessage());
	}

	public static Stream<Arguments> provideForNumbersOutOfSizeExceptionTest() {
		return Stream.of(
				Arguments.of(Arrays.asList(1, 2, 3, 4, 5)),
				Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7)),
				Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8))
		);
	}

	@DisplayName("숫자 요소는 중복될 수 없습니다.")
	@ParameterizedTest
	@MethodSource("provideForNumbersDuplicatedExceptionTest")
	void ticketNumbersDuplicatedExceptionTest(final List<Integer> numbers) {
		assertThatThrownBy(() -> new Ticket(numbers))
				.isInstanceOf(LottoException.class)
				.hasMessageContaining(TicketNumbersExceptionStatus.NUMBERS_DUPLICATED.getMessage());
	}

	public static Stream<Arguments> provideForNumbersDuplicatedExceptionTest() {
		return Stream.of(
				Arguments.of(Arrays.asList(1, 1, 3, 4, 5, 6)),
				Arguments.of(Arrays.asList(1, 1, 1, 4, 5, 3)),
				Arguments.of(Arrays.asList(5, 2, 3, 4, 5, 5))
		);
	}

	@DisplayName("볼 포함 여부 확인 테스트")
	@ParameterizedTest
	@MethodSource("provideForContainsTest")
	void containsTest(final List<Integer> numbers, final int targetNumber) {
		final Ticket ticket = new Ticket(numbers);
		final Ball targetBall = new Ball(targetNumber);

		assertThat(ticket.contains(targetBall)).isTrue();
	}

	public static Stream<Arguments> provideForContainsTest() {
		return Stream.of(
				Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 1),
				Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 3),
				Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 6)
		);
	}

	@DisplayName("당첨 번호와 일치 개수 확인")
	@ParameterizedTest
	@MethodSource("provideForCountMatchesTest")
	void countMatches(final List<Integer> numbers, final List<Integer> winningNumbers, final int matchCount) {
		final Ticket ticket = new Ticket((numbers));
		final Ticket winningTicket = new Ticket(winningNumbers);
		final List<Integer> winningBallNumbers = winningTicket.getBallNumbers();
		final List<Ball> winningBalls = winningBallNumbers.stream()
				.map(Ball::new)
				.collect(Collectors.toUnmodifiableList());

		assertThat(ticket.countMatches(winningBalls)).isEqualTo(matchCount);
	}

	public static Stream<Arguments> provideForCountMatchesTest() {
		return Stream.of(
				Arguments.of(
						Arrays.asList(1, 2, 3, 4, 5, 6),
						Arrays.asList(11, 12, 13, 14, 15, 16),
						0
				),
				Arguments.of(
						Arrays.asList(1, 2, 3, 4, 5, 6),
						Arrays.asList(1, 2, 3, 14, 15, 16),
						3
				),
				Arguments.of(
						Arrays.asList(1, 2, 3, 4, 5, 6),
						Arrays.asList(1, 2, 3, 4, 5, 6),
						6
				)
		);
	}

	@DisplayName("당첨 등수 확인 테스트")
	@ParameterizedTest
	@MethodSource("provideForCalculateRankTest")
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

	public static Stream<Arguments> provideForCalculateRankTest() {
		return Stream.of(
				Arguments.of(
						Arrays.asList(1, 2, 3, 4, 5, 6),
						Arrays.asList(11, 12, 13, 14, 15, 16),
						17, null
				),
				Arguments.of(
						Arrays.asList(1, 2, 3, 4, 5, 6),
						Arrays.asList(1, 2, 3, 14, 15, 16),
						17, Rank.FIFTH_GRADE
				),
				Arguments.of(
						Arrays.asList(1, 2, 3, 4, 5, 6),
						Arrays.asList(1, 2, 3, 4, 5, 16),
						6, Rank.SECOND_GRADE
				),
				Arguments.of(
						Arrays.asList(1, 2, 3, 4, 5, 6),
						Arrays.asList(1, 2, 3, 4, 5, 6),
						7, Rank.FIRST_GRADE
				)
		);
	}

}
