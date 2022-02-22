package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

	//- **로또 갯수 만큼 로또를 발행한다.**  (List<Lotto> Lottos) // Collections.shuffle() 사용

	@Test
	void 로또_발행(){
		assertThat(new LottoMachine().createAutoLotto()).isInstanceOf(Lotto.class);
	}
}
