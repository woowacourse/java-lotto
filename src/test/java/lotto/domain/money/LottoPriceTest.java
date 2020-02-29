package lotto.domain.money;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoPriceTest {

	@DisplayName("지불한 금액이 들어오면 (구매하는) 전체 로또 장수를 반환")
	@ParameterizedTest
	@CsvSource(value = {"1000:1", "14000:14"}, delimiter = ':')
	void calculateCountOfLotto_validMoney_returnCountOfLotto(int money, int countOfLotto) {
		Money paidMoney = new Money(money);
		assertThat(LottoPrice.calculateCountOfLotto(paidMoney)).isEqualTo(countOfLotto);
	}
}