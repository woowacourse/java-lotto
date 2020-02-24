package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class LottoMachineTest {
	@Test
	void 주어진_개수만큼_자동_로또_사기() {
		LottoMachine lottoMachine = LottoMachine.getInstance();
		assertThat(lottoMachine.makeRandomLottos(6).size()).isEqualTo(6);
	}
}
