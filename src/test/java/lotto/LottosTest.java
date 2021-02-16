package lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottosTest {
    private static final String MONEY_UNIT_ERROR = "[ERROR] 금액을 1000단위로 입력해주세요";
    private static final String MONEY_INT_ERROR = "[ERROR] 숫자만 입력할 수 있습니다";
    private static final String MONEY_RANGE_ERROR = "[ERROR] 금액을 1000원 이상 입력해주세요";

    @Test
    void 구입금액이_숫자인지_확인() {
        String falseMoney = "로또값";
        assertThatThrownBy(() -> {
            new Lottos(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MONEY_INT_ERROR);
    }

    @Test
    void 구입금액이_1000이상인지_확인() {
        String falseMoney = "500";
        assertThatThrownBy(() -> {
            new Lottos(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MONEY_RANGE_ERROR);
    }

    @Test
    void 구입금액이_1000단위인지_확인() {
        String falseMoney = "1500";
        assertThatThrownBy(() -> {
            new Lottos(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MONEY_UNIT_ERROR);
    }
}
