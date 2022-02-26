package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottosTest {

    private static final int LOTTO_SIZE = 6;

    @Test
    public void 로또뭉치_생성_성공() {
        assertThat(new Lottos(LOTTO_SIZE).getLottos()).hasSize(LOTTO_SIZE);
    }

    /*
    @ParameterizedTest
    @ValueSource(ints = {-1, 300, 2500})
    public void 로또뭉치_생성_실패(int money) {
        assertThatThrownBy(() -> new Lottos(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Validation.ERROR_WRONG_INPUT_MONEY);
    }
     */
}
