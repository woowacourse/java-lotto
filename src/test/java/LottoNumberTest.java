import domain.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoNumberTest {

	@Test
	void numberInRange() {
		assertThatThrownBy(() -> LottoNumber.from(50))
			.isInstanceOf(IllegalArgumentException.class);

	}
}
