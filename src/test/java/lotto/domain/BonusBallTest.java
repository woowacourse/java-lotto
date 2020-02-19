package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class BonusBallTest {

	@Test
	void isMatchBonusBall() {
		BonusBall bonusBall = new BonusBall("3");
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		assertThat(bonusBall.isMatchBonusBall(numbers)).isTrue();
		bonusBall = new BonusBall("9");
		assertThat(bonusBall.isMatchBonusBall(numbers)).isFalse();

	}
}
