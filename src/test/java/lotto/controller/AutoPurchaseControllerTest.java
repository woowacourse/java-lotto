package lotto.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.config.ControllerConfig;
import lotto.dto.LottoTicketResponse;

class AutoPurchaseControllerTest {

    @Test
    @DisplayName("남은 금액으로 자동 로또를 구매할 수 있다.")
    public void purchaseLottoTicketByString() {
        // given
        String input = "3000";
        MoneyController moneyController = ControllerConfig.getMoneyController();
        moneyController.insertMoney(input);

        AutoPurchaseController autoPurchaseController = ControllerConfig.getAutoPurchaseController();
        // when
        List<LottoTicketResponse> ticketResponses = autoPurchaseController.purchase(1);
        // then
        Assertions.assertThat(ticketResponses.size()).isEqualTo(2);
    }
}