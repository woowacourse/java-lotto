package lotto.domain;

import lotto.exception.IllegalLottoCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoCountTest {
    @ParameterizedTest
    @ValueSource(strings = {"-1", "q", "-", "", "11"})
    @DisplayName("구매할 로또 수 검증")
    void checkLottoCount(String manualCount) {
        int totalCount = 10;
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(manualCount, totalCount);
        }).isInstanceOf(IllegalLottoCountException.class);
    }
}
