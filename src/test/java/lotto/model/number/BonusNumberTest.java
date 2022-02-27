package lotto.model.number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusNumberTest {

    @Test
    void 보너스_번호_생성_테스트() {
        BonusNumber bonusNumber = new BonusNumber(1);
        assertThat(bonusNumber.equals(new BonusNumber(1))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,46})
    void 보너스_번호_생성_테스트_범위1() {
        assertThatThrownBy(() ->
                new BonusNumber(-1))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }
}
