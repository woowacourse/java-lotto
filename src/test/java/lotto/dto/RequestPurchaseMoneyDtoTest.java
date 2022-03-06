package lotto.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RequestPurchaseMoneyDtoTest {

    @DisplayName("숫자를 받아 구입금액을 가지는 dto를 생성한다.")
    @Test
    void response_purchase_money_dto_test() {
        // given
        int money = 1000;

        // when
        RequestPurchaseMoneyDto dto = new RequestPurchaseMoneyDto(money);

        // then
        assertThat(dto.getMoney().get()).isEqualTo(money);
    }
}
