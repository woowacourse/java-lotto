package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class LottosTest {
	@Test
	void add() {
		// given
		List<String> input1 = Arrays.asList(
				"1,2,3,4,5,6",
				"7,2,3,4,5,6",
				"1,7,3,4,5,6",
				"1,2,7,4,5,6");
		List<String> input2 = Arrays.asList(
				"1,2,3,4,7,6",
				"1,2,3,4,5,7");

		Lottos lottos1 = Lottos.of(input1.stream()
				.map(Lotto::of)
				.collect(Collectors.toUnmodifiableList()));
		Lottos lottos2 = Lottos.of(input2.stream()
				.map(Lotto::of)
				.collect(Collectors.toUnmodifiableList()));

		// when
		Lottos result
				= lottos1.add(lottos2);

		// then
		List<String> merged = new ArrayList<>();
		merged.addAll(input1);
		merged.addAll(input2);

		Lottos expected = Lottos.of(merged.stream()
				.map(Lotto::of)
				.collect(Collectors.toUnmodifiableList()));

		Assertions.assertThat(result)
				.isEqualTo(expected);
	}
}