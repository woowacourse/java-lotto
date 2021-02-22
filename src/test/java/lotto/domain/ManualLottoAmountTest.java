package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualLottoAmountTest {

    @DisplayName("수동로또 개수 생성 테스트")
    @Test
    void create() {
        ManualLottoAmount manualLottoAmount = new ManualLottoAmount("5", 7);
        assertThat(manualLottoAmount.getValue()).isEqualTo(5);
    }
}
