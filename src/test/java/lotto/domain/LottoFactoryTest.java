package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
class LottoFactoryTest {
	@Test
	void 생성_테스트() {
		assertThat(LottoFactory.randomlyCreateSingle());
	}

	@Test
	@DisplayName("로또 장수 만큼 로또를 반환하는지")
	void LottosTest() {
		Lottos lottos = LottoFactory.randomlyCreateOf(3);
		assertThat(lottos.getSize()).isEqualTo(3);
	}

	@ParameterizedTest
	@DisplayName("수동으로 생성된 로또를 비교할 수 있다.")
	@CsvSource(value = {"1,2,3,4,5,6:4", "4,7,44,42,33,13:2"}, delimiter = ':')
	void 로또_수동_생성_테스트(String input, int expected) {
		Lotto lotto = LottoFactory.createManualSingle(input);
		List<Number> numbers = Arrays.asList(
			Number.of("3"),
			Number.of("4"),
			Number.of("5"),
			Number.of("6"),
			Number.of("7"),
			Number.of("45")
		);
		Lotto winningLotto = new Lotto(numbers);
		assertThat(lotto.compare(winningLotto)).isEqualTo(expected);
	}

	@Test
	@DisplayName("로또묶음을 생성했을 때 수동과 자동이 합쳐져서 만들어지는지")
	void manualWithAutoLotto() {
		List<String> manualInput = Arrays.asList("1,2,3,4,5,6", "3,4,5,6,7,8");
		assertThat(LottoFactory.createManualAndAuto(manualInput, 7)).hasSize(9);
	}

	@Test
	@DisplayName("수동으로 생성한 로또가 정상적으로 로또 묶음에 들어 있는지")
	void lottosHasManualLotto() {
		List<String> manualInput = Arrays.asList("1,2,3,4,5,6", "3,4,5,6,7,8");
		Lotto manualLotto = new Lotto(Arrays.asList(
			Number.of("1"),
			Number.of("2"),
			Number.of("3"),
			Number.of("4"),
			Number.of("5"),
			Number.of("6")));
		Iterator<Lotto> iter = LottoFactory.createManualAndAuto(manualInput, 7).iterator();

		assertThat(iter.next().compare(manualLotto)).isEqualTo(6);
	}
}