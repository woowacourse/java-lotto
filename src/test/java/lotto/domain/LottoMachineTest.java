package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LottoMachineTest {
	@Test
	void 로또_랜덤번호_6개_뽑기() {
		LottoMachine lottoMachine = new LottoMachine();
		assertThat(lottoMachine.pickRandomBalls()).size().isEqualTo(6);
	}
}
