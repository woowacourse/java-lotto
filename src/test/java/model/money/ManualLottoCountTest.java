package model.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualLottoCountTest {

    @Test
    @DisplayName("수동으로 구매한 로또 개수 객체를 생성한다.")
    void generateManualLottoCount() {
        ManualLottoCount manualLottoCount = new ManualLottoCount(3);

        assertThat(manualLottoCount.getCount()).isEqualTo(3);
    }

}