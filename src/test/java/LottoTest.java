import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class LottoTest {

	@Test
	void numberListMustBeSix() {
		Lotto lotto = new Lotto(() -> Arrays.asList(1,2,3,4,5,6));
		assertThat(lotto.getNumbersSize()).isEqualTo(6);
	}
}
