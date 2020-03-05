package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {
	@Test
	@DisplayName("주어진 개수만큼 자동 로또 사기")
	void buyAutoLottoTest() {
		LottoMachine lottoMachine = LottoMachine.getInstance();
		assertThat(lottoMachine.makeRandomLottos(new LottoCount(6000, 0)).size()).isEqualTo(6);
	}

	@ParameterizedTest
	@DisplayName("수동 로또 구매하기")
	@ValueSource(ints = {1, 2, 3, 4, 5, 6})
	void pickDedicatedBallsTest(int value) {
		LottoMachine lottoMachine = LottoMachine.getInstance();
		Lotto manualLotto = lottoMachine.pickDedicatedBalls(Arrays.asList(1, 2, 3, 4, 5, 6));
		assertThat(manualLotto.contains(lottoMachine.pickBall(value))).isTrue();
	}
}
