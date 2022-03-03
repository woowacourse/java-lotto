package lotto.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.config.ControllerConfig;
import lotto.domain.Money;
import lotto.dto.LottoTicketResponse;
import lotto.repository.MoneyRepository;

class ManualPurchaseControllerTest {

    @Test
    @DisplayName("입력받은 번호로 로또를 구매한다.")
    public void purchaseLottoByInput() {
        // given
        MoneyRepository moneyRepository = MoneyRepository.getInstance();
        moneyRepository.save(Money.from(2000));

        // when
        ManualPurchaseController manualPurchaseController = ControllerConfig.getManualPurchaseController();
        List<String> inputNumbers = List.of("1,2,3,4,5,6", "7,8,9,10,11,12");
        // then
        List<LottoTicketResponse> responses = manualPurchaseController.purchase(inputNumbers);
        Assertions.assertThat(responses.size()).isEqualTo(2);
    }
}