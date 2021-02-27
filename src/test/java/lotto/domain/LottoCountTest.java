package lotto.domain;

import lotto.domain.lotto.LottoCount;
import lotto.utils.ParseUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoCountTest {
    @ParameterizedTest
    @ValueSource(strings = {"q", "-", ""})
    @DisplayName("구매할 로또 수 포맷 검증")
    void checkLottoCount(String manualCount) {
        int totalCount = 10;
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(ParseUtil.parseInt(manualCount), totalCount);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "11", "15"})
    @DisplayName("구매 가능한 로또 개수 범위가 아닌 경우")
    void checkLottoCount2(String manualCount) {
        int totalCount = 10;
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(ParseUtil.parseInt(manualCount), totalCount);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
