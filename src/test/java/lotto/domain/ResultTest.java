package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

public class ResultTest {
	@Test
	void findTest() {
		Result result = Result.of(5);
		assertThat(result == Result.TWO).isTrue();
	}
}
