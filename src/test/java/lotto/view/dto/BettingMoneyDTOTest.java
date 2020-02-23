package lotto.view.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

//TODO: 티켓값보다 적은 금액 입력시, null 혹은 empty, 숫자 아닌 값 입력시
class BettingMoneyDTOTest {

    @DisplayName("예외 테스트: 로또 한 장 가격보다 작은 값 입력시 Exception 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999})
    void test1(int money) {
        assertThatThrownBy(() -> new BettingMoneyDTO(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%d는 최소 구매 금액보다 작습니다.", money);
    }

    @DisplayName("입력받은 값 반환 테스트")
    @Test
    void test2() {
        //given
        int inputBettingMoney = 1000;

        //when
        BettingMoneyDTO bettingMoneyDTO = new BettingMoneyDTO(inputBettingMoney);

        //then
        assertThat(bettingMoneyDTO.getBettingMoney()).isEqualTo(inputBettingMoney);
    }
}
