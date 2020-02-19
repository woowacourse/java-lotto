package lotto.domain;

import lotto.exceptions.LottoTicketIllegalArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTicketTest {
	@Test
	void LottoTicket() {
		// given
		int[] input = {1, 10, 3, 11, 5, 6};
		List<LottoNumber> lottoNumbers = Arrays.stream(input)
				.mapToObj(LottoNumber::new)
				.collect(Collectors.toList());

		// when
		LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

		// then
		int[] sortedInput = {1, 3, 5, 6, 10, 11};
		List<LottoNumber> expected = Arrays.stream(sortedInput)
				.mapToObj(LottoNumber::new)
				.collect(Collectors.toList());

		Assertions.assertThat(lottoTicket.getLottoNumbers())
				.isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource("generateNotSizeSixInput")
	void LottoTicket_NotSizeSix_ShouldThrowException(int[] input) {
		// given
		List<LottoNumber> lottoNumbers = Arrays.stream(input)
				.mapToObj(LottoNumber::new)
				.collect(Collectors.toList());

		// then
		Assertions.assertThatThrownBy(() -> {

			// when
			new LottoTicket(lottoNumbers);
		}).isInstanceOf(LottoTicketIllegalArgumentException.class)
		.hasMessageMatching(LottoTicketIllegalArgumentException.MESSAGE);
	}

	static Stream<Arguments> generateNotSizeSixInput() {
		return Stream.of(Arguments.of(new int[]{1, 2, 3, 4, 5}),
				Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7}));
	}

	@ParameterizedTest
	@MethodSource("generateDuplicatedInput")
	void LottoTicket_DuplicatedNumbers_ShouldThrowException(int[] input) {
		// given
		List<LottoNumber> lottoNumbers = Arrays.stream(input)
				.mapToObj(LottoNumber::new)
				.collect(Collectors.toList());

		// then
		Assertions.assertThatThrownBy(() -> {

			// when
			new LottoTicket(lottoNumbers);
		}).isInstanceOf(LottoTicketIllegalArgumentException.class)
				.hasMessageMatching(LottoTicketIllegalArgumentException.MESSAGE);
	}

	static Stream<Arguments> generateDuplicatedInput() {
		return Stream.of(Arguments.of(new int[]{1, 2, 3, 4, 4, 5}),
				Arguments.of(new int[]{1, 3, 2, 3, 4, 3}),
				Arguments.of(new int[]{1, 2, 3, 1, 2, 3}));
	}
}
