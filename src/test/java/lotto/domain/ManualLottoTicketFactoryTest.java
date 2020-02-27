package lotto.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


class ManualLottoTicketFactoryTest {
	@Test
    @DisplayName("수동 로또장수 입력이 정수가 아닐경우 익셉션을 발생한다")
	void createManualLottoTicketTest() {
		PurchasingAmount purchasingAmount = new PurchasingAmount(1000);
		String manualTicketValue1 = "a";

		assertThatIllegalArgumentException().isThrownBy(() ->
				new ManualLottoTicketFactory(manualTicketValue1, purchasingAmount)).withMessage("정수만 입력하십시오");
	}

	@Test
    @DisplayName("구입금액 보다 많은 수동 로또를 구매할경우 익셉션을 발생한다")
    void createManualLottoTicketTest2() {
		PurchasingAmount purchasingAmount = new PurchasingAmount(1000);
		String manualTicketValue1 = "5";

		assertThatIllegalArgumentException().isThrownBy(() ->
				new ManualLottoTicketFactory(manualTicketValue1, purchasingAmount)).withMessage("구매 금액 초과의 티켓은 구매 불가합니다");
	}
}
