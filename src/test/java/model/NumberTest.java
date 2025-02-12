package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class NumberTest {

    @Test
    void 로또_번호는_1부터_45사이여야_한다() {
        Number number = new Number(40);
        assertThat(number.value()).isEqualTo(40);
    }

    @Test
    void 로또_번호가_1부터_45사이가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> {
            Number number = new Number(46);
        }).isInstanceOf(RuntimeException.class);
    }
}
