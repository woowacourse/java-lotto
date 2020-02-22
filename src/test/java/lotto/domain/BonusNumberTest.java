package lotto.domain;

import lotto.exceptions.LottoNumberRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusNumberTest {
    @Test
    @DisplayName("보너스넘버가 적정 범위 안에 있을 때")
    void initTest() {
        BonusNumber bonusNumber = new BonusNumber(1);
        assertThat(bonusNumber).isNotNull();
    }

    @Test
    @DisplayName("보너스 넘버가 범위 안에 없을 때 Exception 나오는 테스트")
    void initExceptionTest() {
        assertThatThrownBy(() -> new BonusNumber(46))
                .isInstanceOf(LottoNumberRangeException.class)
                .hasMessageContaining("범위");
    }
}