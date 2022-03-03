package lotto.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.config.ControllerConfig;
import lotto.dto.LottoTicketResponse;

class AutoPurchaseControllerTest {

    @Test
    @DisplayName("금액으로 입력한 문자열로 로또를 구매할 수 있다.")
    public void purchaseLottoTicketByString() {
        // given
        String input = "2000";
        AutoPurchaseController controller = ControllerConfig.getAutoPurchaseController();
        // when
        List<LottoTicketResponse> ticketResponses = controller.purchase(input);
        // then
        Assertions.assertThat(ticketResponses.size()).isEqualTo(2);
    }
}