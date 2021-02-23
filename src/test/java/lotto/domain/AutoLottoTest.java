package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoLottoTest {

    @DisplayName("자동 로또 생성 확인")
    @Test
    void create_auto_lotto() {
        AutoLotto autoLotto = AutoLotto.createAutoLotto(6);

        assertThat(autoLotto.getAutoLotto()).hasSize(6);
    }
}
