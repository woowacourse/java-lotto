package lotto.domain;

import lotto.domain.SerialLottoNumberFactory.TestLottoFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
	@Test
	void of() {
		// given
		PurchasedLottos purchasedLottos
				= PurchasedLottos.of(LottoMoney.of(14000),
				new TestLottoFactory());

		WinningLotto winningLotto
				= WinningLotto.of(Lotto.of("1, 2, 3, 4, 5, 6"), LottoNumber.of(7));

		// when
		LottoResult result = LottoResult.of(purchasedLottos, winningLotto);

		// then
		Assertions.assertThat(result.getLottoResult().get(WinningType.FIRST_PLACE))
				.isEqualTo(14);
	}
}
