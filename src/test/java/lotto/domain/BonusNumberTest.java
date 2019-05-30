package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class BonusNumberTest {
    @Test
    void 생성자_확인() {
        assertThat(new BonusNumber(2)).isInstanceOf(BonusNumber.class);
    }

    @Test
    void 생성자_확인_범위를_벗어나는_숫자가_입력된_경우() {
        assertThatThrownBy(() -> new BonusNumber(46)).isInstanceOf(NullPointerException.class);
    }
}
