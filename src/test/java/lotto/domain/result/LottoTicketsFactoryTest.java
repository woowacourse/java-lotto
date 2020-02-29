package lotto.domain.result;

import lotto.domain.number.SerialLottoNumber;
import lotto.domain.number.SerialLottoNumberFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LottoTicketsFactoryTest {
	@Test
	void createByInput() {
		// given
		List<String> input = Arrays.asList("1,2,3,4,5,6",
				"5,6,2,1,8,13",
				"1,2,6,43,45,34");

		// when
		LottoTickets result = LottoTicketsFactory.of(input);

		// then
		Assertions.assertThat(result).isEqualTo(LottoTicketsFactory.of(input));

	}
}
