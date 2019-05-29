package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaymentTest {
    @Test
    void 생성자_확인() {
        assertThat(new Payment(1000)).isEqualTo(new Payment(1000));
    }

    @Test
    void 생성자_확인_음수가_입력되었을_때() {
        assertThatThrownBy(() -> new Payment(-1)).isInstanceOf(IllegalArgumentException.class);
    }
}
