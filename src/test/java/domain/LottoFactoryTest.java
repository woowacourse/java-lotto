package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoFactoryTest {

	private static final LottoFactory lottoFactory = new LottoFactory();

	@Test
	@DisplayName("구입한 금액 만큼 로또 발급")
	void generateLottoByMoney() {
		String purchaseMoney = "14000";
		Money money = Money.from(purchaseMoney);

		assertThat(lottoFactory.generateLottoTicket(money).size()).isEqualTo(Integer.parseInt(purchaseMoney) / 1000);
	}
}
