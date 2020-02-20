package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

public class BonusBallTest {

	@Test
	void isContainBonusBall() {
		BonusBall bonusBall = new BonusBall("3");
		List<LottoNo> numbers = IntStream.range(1, 7)
				.boxed()
				.map(LottoNo::new)
				.collect(Collectors.toList());
		Lotto lotto = new Lotto(numbers);
		assertThat(bonusBall.isContainBonusBall(lotto)).isTrue();
		bonusBall = new BonusBall("9");
		assertThat(bonusBall.isContainBonusBall(lotto)).isFalse();
	}
}
