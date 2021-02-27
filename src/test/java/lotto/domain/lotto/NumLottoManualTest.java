package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

public class NumLottoManualTest {

    @Test
    void 수동_구매_숫자_테스트() {
        NumManualLotto numManualLotto = new NumManualLotto("1");
        assertThat(numManualLotto.getNumLotto()).isEqualTo(1);
    }

    @Test
    void 수동_구매_음수() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            NumManualLotto numManualLotto = new NumManualLotto("-1");
        });
    }

    @Test
    void 수동_구매_문자() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            NumManualLotto numManualLotto = new NumManualLotto("TEST");
        });
    }

    @Test
    void 수동_구매_공백() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            NumManualLotto numManualLotto = new NumManualLotto("1 23");
        });
    }
}
