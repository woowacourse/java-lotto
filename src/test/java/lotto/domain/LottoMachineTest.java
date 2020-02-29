package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {
	@Test
	@DisplayName("주어진 개수만큼 자동 로또 사기")
	void buyAutoLottoTest() {
		LottoMachine lottoMachine = LottoMachine.getInstance();
		assertThat(lottoMachine.makeRandomLottos(6).size()).isEqualTo(6);
	}

	@Test
	@DisplayName("수동 로또 구매하기")
	void pickDedicatedBallsTest() {
		LottoMachine lottoMachine = LottoMachine.getInstance();
		assertThat(lottoMachine.pickDedicatedBalls(Arrays.asList(1, 2, 3, 4, 5, 6)))
				.contains(lottoMachine.pickBall(1))
				.contains(lottoMachine.pickBall(2))
				.contains(lottoMachine.pickBall(3))
				.contains(lottoMachine.pickBall(4))
				.contains(lottoMachine.pickBall(5))
				.contains(lottoMachine.pickBall(6));
	}
}
