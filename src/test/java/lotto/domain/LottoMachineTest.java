package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {
	@Test
	@DisplayName("주어진 개수만큼 자동 로또 사기")
	void buyAutoLotto() {
		LottoMachine lottoMachine = new LottoMachine();
		assertThat(lottoMachine.makeRandomLottos(new LottoCount(6000)).size()).isEqualTo(6);
	}
}
