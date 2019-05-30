package lotto.model.object;

import lotto.model.creator.PaymentCreator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class PaymentTest {
        @Test
        void 수익률_계산_검사() {
                assertThat(assertThat(PaymentCreator.create(1000).calculateYield(500)).isEqualTo(0.5, offset(0.00001)));
        }

        @Test
        void 구매_가능_숫자_검사() {
                assertThat(assertThat(PaymentCreator.create(3000).isPurchasable(2)).isEqualTo(true));
        }
}