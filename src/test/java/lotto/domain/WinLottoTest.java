package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinLottoTest {
	private WinLotto winLotto;

	@BeforeEach
	void setUp() {
		winLotto = new WinLotto("1,2,3,4,5,6", "7");
	}

	@DisplayName("당첨 로또와 비교하여 맞은 개수 테스트")
	@Test
	void compareTest() {
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
		assertThat(winLotto.compare(lotto)).isEqualTo(6);
		lotto = new Lotto(Arrays.asList(7, 8, 9, 10, 11, 12));
		assertThat(winLotto.compare(lotto)).isEqualTo(0);
	}

	@DisplayName("전달받은 로또에서 보너스볼과 일치하는게 있는지 테스트")
	@Test
	void isMatchBonus() {
		Lotto lotto = new Lotto(Arrays.asList(7, 8, 9, 10, 11, 12));
		assertThat(winLotto.isMatchBonus(lotto)).isTrue();
	}
}
