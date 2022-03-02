package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NoneAsciiCharacters")
public class ManualLottoCountTest {
    @Test
    void 최대개수를_넘는_값을_입력한_경우_예외발생() {
        int inputCount = 3;
        int upperBound = 2;

        assertThatThrownBy(() -> ManualLottoCount.of(inputCount, upperBound)).isInstanceOf(
                IllegalArgumentException.class).hasMessageContaining("최대 구매 개수를 초과했습니다.");
    }

    @Test
    void 최대개수를_넘지않는_경우() {
        int inputCount = 2;
        int upperBound = 2;

        assertThatNoException().isThrownBy(() -> ManualLottoCount.of(inputCount, upperBound));
    }
}
