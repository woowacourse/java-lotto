package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class BonusBallTest {

	@Test
	void isContainBonusBall() {
		BonusBall bonusBall = new BonusBall("3");
		List<LottoNo> numbers = new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			numbers.add(new LottoNo(i));
		}
		Lotto lotto = new Lotto(numbers);
		assertThat(bonusBall.isContainBonusBall(lotto)).isTrue();
		bonusBall = new BonusBall("9");
		assertThat(bonusBall.isContainBonusBall(lotto)).isFalse();
	}
}
