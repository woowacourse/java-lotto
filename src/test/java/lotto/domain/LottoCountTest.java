package lotto.domain;

import lotto.exception.IllegalLottoCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoCountTest {
    @ParameterizedTest
    @ValueSource(strings = {"-1", "q", "-"})
    @DisplayName("구매할 로또 수 검증")
    void checkLottoCount(String count) {
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(count);
        }).isInstanceOf(IllegalLottoCountException.class);
    }
}
