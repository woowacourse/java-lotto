package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class LottosTest {
	@Test
	void of_Strings() {
		// given
		String[] input = {"1, 10, 3, 11, 5, 6", "5, 10, 45, 3, 17, 2", "4, 7, 13, 19, 22, 37"};

		// when
		Lottos result = Lottos.of(input);

		// then
		List<Lotto> expected = Stream.of(input)
				.map(Lotto::of)
				.collect(Collectors.toUnmodifiableList());

		Assertions.assertThat(result.getLottos())
				.isEqualTo(expected);
	}

	@Test
	void add() {
		// given
		Lottos lottos
				= Lottos.of(
				"1,2,3,4,5,6",
				"7,2,3,4,5,6",
				"1,7,3,4,5,6",
				"1,2,7,4,5,6");

		Lottos lottos2
				= Lottos.of(
				"1,2,3,4,7,6",
				"1,2,3,4,5,7");

		// when
		Lottos result
				= lottos.add(lottos2);

		// then
		Lottos expected
				= Lottos.of(
				"1,2,3,4,5,6",
				"7,2,3,4,5,6",
				"1,7,3,4,5,6",
				"1,2,7,4,5,6",
				"1,2,3,4,7,6",
				"1,2,3,4,5,7");

		Assertions.assertThat(result)
				.isEqualTo(expected);
	}
}