package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void 금액이_천원_단위가_아니면_예외가_발생한다() {
        Assertions.assertThatThrownBy(() -> new Money(1500))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("금액은 1,000원 단위로 입력해 주세요.");
    }

    @Test
    void 금액은_0원보다_커야_한다() {
        Assertions.assertThatThrownBy(() -> new Money(0))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("금액은 1,000원 이상이여야 합니다.");
    }
}
