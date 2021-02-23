package lotto.domain;

import lotto.domain.lotto.LottoCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoCountTest {
    @ParameterizedTest
    @ValueSource(strings = {"-1", "q", "-", ""})
    @DisplayName("구매할 로또 수 포맷 검증")
    void checkLottoCount(String manualCount) {
        int totalCount = 10;
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(manualCount, totalCount);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구매 가능한 로또 개수 초과했을 경우")
    void checkLottoCount2() {
        int totalCount = 10;
        String manualCount = "11";
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(manualCount, totalCount);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
