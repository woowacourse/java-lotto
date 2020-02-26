package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

public class WinLottoTest {
	private WinLotto winLotto;

	@BeforeEach
	void setUp() {
		String[] winNumbers = new String[]{"1", "2", "3", "4", "5", "6"};
		winLotto = new WinLotto(winNumbers, "7");
	}

	@DisplayName("당첨 로또와 비교하여 맞은 개수 테스트")
	@Test
	void compareTest() {
        Set<LottoNo> numbers = IntStream.range(1, 7)
                .boxed()
                .map(LottoNo::new)
                .collect(Collectors.toSet());
        Lotto lotto = new Lotto(numbers);
        assertThat(winLotto.findHitCount(lotto)).isEqualTo(6);

        numbers = IntStream.range(7, 13)
                .boxed()
                .map(LottoNo::new)
                .collect(Collectors.toSet());
        lotto = new Lotto(numbers);
		assertThat(winLotto.findHitCount(lotto)).isEqualTo(0);
	}

	@DisplayName("전달받은 로또에서 보너스볼과 일치하는게 있는지 테스트")
	@Test
	void isMatchBonus() {
		Set<LottoNo> numbers = IntStream.range(7, 13)
				.boxed()
				.map(LottoNo::new)
				.collect(Collectors.toSet());
		Lotto lotto = new Lotto(numbers);
		assertThat(winLotto.isMatchBonus(lotto)).isTrue();
	}

	@DisplayName("보너스볼과 당첨번호가 중복 예외처리")
	@Test
	void BonusBallInWinLotto() {
		String[] winLotto = new String[]{"1", "2", "3", "4", "5", "6"};
		String bonusBall = "4";
		assertThatThrownBy(() -> new WinLotto(winLotto, bonusBall))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("당첨번호에 보너스볼이 포함되어 있습니다.");
	}
}
