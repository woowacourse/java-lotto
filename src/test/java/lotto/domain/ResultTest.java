package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ResultTest {
	@Test
	void findTest() {
		Result result = Result.of(5, true);
		assertThat(result == Result.TWO).isTrue();
	}

	@Test
	void sumTest() {
		List<Result> results = Arrays.asList(Result.FIVE, Result.THREE);
		assertThat(Result.sum(results)).isEqualTo(1505000);
	}
}
