package lotto.view.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyRequestDTOTest {

    @DisplayName("잘못된 입력값 주입시")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999})
    void test1(int money) {
        assertThatThrownBy(() -> new BettingMoneyRequestDTO(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%d는 최소 구매 금액보다 작습니다.", money);
    }

    @DisplayName("입력받은 값 반환 테스트")
    @Test
    void test2() {
        //given
        int inputBettingMoney = 1000;

        //when
        BettingMoneyRequestDTO bettingMoneyRequestDTO = new BettingMoneyRequestDTO(inputBettingMoney);

        //then
        assertThat(bettingMoneyRequestDTO.getBettingMoney()).isEqualTo(inputBettingMoney);
    }
}