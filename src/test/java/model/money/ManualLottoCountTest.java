package model.money;

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
        ManualLottoCount manualLottoCount = new ManualLottoCount(3, 10);

        assertThat(manualLottoCount.getCount()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -34})
    @DisplayName("수동으로 구매할 로또 개수가 0개보다 적으면 오류를 발생한다.")
    void checkValidCount_UnderMinCount(int count) {
        assertThatThrownBy(() -> new ManualLottoCount(count, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 수동 구매 로또 개수가 최소로 구매할 개수보다 작습니다.");
    }

    @Test
    @DisplayName("수동으로 구매할 로또 개수가 최대로 구매 가능한 로또 개수보다 많으면 오류를 발생한다.")
    void checkValidCount_OverMaxCount() {
        int purchaseLottoCount = 10;
        assertThatThrownBy(() -> new ManualLottoCount(11, purchaseLottoCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 수동 구매 로또 개수가 최대로 구매할 수 있는 개수보다 많습니다.");
    }
}