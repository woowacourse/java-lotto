package lotto.domain.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingInfoTest {

    @DisplayName("베팅 금액과 수동갯수를 이용하여 자동 갯수와 수동갯수 구하기")
    @Test
    void getTicketAmount() {
        //given
        BettingMoney bettingMoney = BettingMoney.valueOf(10000);
        int manualAmount = 3;

        //when
        BettingInfo bettingInfo = new BettingInfo(bettingMoney, manualAmount);

        //then
        assertThat(bettingInfo.getRandomAmount()).isEqualTo(7);
        assertThat(bettingInfo.getManualAmount()).isEqualTo(3);
    }

    @DisplayName("입력받은 베팅금액으로 입력받은 수동갯수를 구매 할 수 없는 경우 Exception 발생")
    @Test
    void bettingInfoAmountException() {
        BettingMoney bettingMoney = BettingMoney.valueOf(10000);
        int manualAmount = 100;

        assertThatThrownBy(() -> new BettingInfo(bettingMoney, manualAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 구매의 갯수(%d)가 입력 금액(%d)을 초과하였습니다.", 100, 10000);
    }

}