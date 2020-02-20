package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class BonusBallTest {

	@Test
	void isContainBonusBall() {
		BonusBall bonusBall = new BonusBall("3");
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		Lotto lotto = new Lotto(numbers);
		assertThat(bonusBall.isContainBonusBall(lotto)).isTrue();
		bonusBall = new BonusBall("9");
		assertThat(bonusBall.isContainBonusBall(lotto)).isFalse();
	}
}
