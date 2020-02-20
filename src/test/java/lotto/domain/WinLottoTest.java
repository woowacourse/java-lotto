package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		List<LottoNo> numbers = IntStream.range(1, 7)
			.boxed()
			.map(LottoNo::new)
			.collect(Collectors.toList());
		Lotto lotto = new Lotto(numbers);
		assertThat(winLotto.compare(lotto)).isEqualTo(6);

		numbers = IntStream.range(7, 13)
			.boxed()
			.map(LottoNo::new)
			.collect(Collectors.toList());
		lotto = new Lotto(numbers);
		assertThat(winLotto.compare(lotto)).isEqualTo(0);
	}

	@DisplayName("전달받은 로또에서 보너스볼과 일치하는게 있는지 테스트")
	@Test
	void isMatchBonus() {
		List<LottoNo> numbers = IntStream.range(7, 13)
			.boxed()
			.map(LottoNo::new)
			.collect(Collectors.toList());
		Lotto lotto = new Lotto(numbers);
		assertThat(winLotto.isMatchBonus(lotto)).isTrue();
	}
}
