package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoFactoryTest {

	private static final LottoFactory lottoFactory = new LottoFactory();

	@Test
	@DisplayName("구입한 금액 만큼 로또 발급")
	void generateLottoByMoney() {
		final int purchaseMoney = 14000;
		final int expected = purchaseMoney / 1000;

		final Money money = new Money(purchaseMoney);
		final int actual = lottoFactory.generateLottoTicket(money).size();

		assertThat(actual).isEqualTo(expected);
	}
}
