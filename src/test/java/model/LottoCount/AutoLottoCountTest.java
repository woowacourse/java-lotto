package model.LottoCount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AutoLottoCountTest {

    @Test
    @DisplayName("자동으로 구매할 로또 개수 객체를 생성한다.")
    void generateManualLottoCount() {
        final AutoLottoCount autoLottoCount = new AutoLottoCount(3);

        assertThat(autoLottoCount.getCount()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -34})
    @DisplayName("자동으로 구매할 로또 개수가 0개보다 적으면 오류를 발생한다.")
    void checkValidCount_UnderMinCount(int count) {
        assertThatThrownBy(() -> new AutoLottoCount(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 자동 구매 로또 개수가 음수입니다.");
    }
}