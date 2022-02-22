import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;

import org.junit.jupiter.api.Test;

public class LottoTest {

	@Test
	void numberListMustBeSix() {
		Lotto lotto = new Lotto();
		assertThat(lotto.getNumbersSize()).isEqualTo(6);
	}

}
