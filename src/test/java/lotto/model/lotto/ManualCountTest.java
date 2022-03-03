package lotto.model.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ManualCountTest {

    @Test
    @DisplayName("수동 로또 생성 종료 테스트")
    void checkMakeManualEnd() {
        ManualCount manualCount = new ManualCount(1);
        manualCount.createManualLotto();

        assertThat(manualCount.isEnd()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 100})
    @DisplayName("수동 로또 생성 개수 감소 테스트")
    void checkManualLottoCount(int number) {
        ManualCount manualCount = new ManualCount(number);
        manualCount.createManualLotto();

        assertThat(manualCount.getNumber()).isEqualTo(number - 1);
    }
}
