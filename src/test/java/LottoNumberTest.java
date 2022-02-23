import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.LottoNumber;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	void lottoNumberInRange(int input) {
		assertThatThrownBy(() -> new LottoNumber(input)).isInstanceOf(IllegalArgumentException.class);
	}
}
