package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.Money;
import org.junit.jupiter.api.Test;

public class NumLottoManualTest {

    @Test
    void 수동_구매_숫자_테스트() {
        ManualLotto manualLotto = new ManualLotto("1");
        assertThat(manualLotto.getNumLotto()).isEqualTo(1);
    }

    @Test
    void 수동_구매_음수() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            ManualLotto manualLotto = new ManualLotto("-1");
        });
    }

    @Test
    void 수동_구매_문자() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            ManualLotto manualLotto = new ManualLotto("TEST");
        });
    }

    @Test
    void 수동_구매_공백() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            ManualLotto manualLotto = new ManualLotto("1 23");
        });
    }
}
