package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BonusBallTest {

	@DisplayName("1~6까지의 로또번호를 가진 로또 생성 후 보너스볼이 포함되어있는지 테스트")
	@Test
	void isContainBonusBall() {
		List<LottoNo> numbers = new ArrayList<>();
		for (int cnt = 1; cnt <= 6; cnt++) {
			numbers.add(new LottoNo(cnt));
		}
		Lotto lotto = new Lotto(numbers);

		BonusBall bonusBall = new BonusBall("3");
		assertThat(bonusBall.isContainBonusBall(lotto)).isTrue();

		bonusBall = new BonusBall("9");
		assertThat(bonusBall.isContainBonusBall(lotto)).isFalse();
	}
}
