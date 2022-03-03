package lotto.domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseLottoCountTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "13"})
    public void 구입개수입력_성공(String count) {
        new PurchaseLottoCount(count, 14);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "15", "ABC",})
    public void 구입개수입력_실패(String count) {
        assertThatThrownBy(() -> new PurchaseLottoCount(count, 14))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
