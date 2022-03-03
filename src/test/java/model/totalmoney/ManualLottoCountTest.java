package model.totalmoney;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ManualLottoCountTest {

    @Test
    @DisplayName("수동으로 구매할 로또 개수 객체를 생성한다.")
    void generateManualLottoCount() {
        final ManualLottoCount manualLottoCount = new ManualLottoCount(3);

        assertThat(manualLottoCount.getCount()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -34})
    @DisplayName("수동으로 구매할 로또 개수가 0개보다 적으면 오류를 발생한다.")
    void checkValidCount_UnderMinCount(int count) {
        assertThatThrownBy(() -> new ManualLottoCount(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 수동 구매 로또 개수가 음수입니다.");
    }
}