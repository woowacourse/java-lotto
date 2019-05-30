package lotto.model.creator;

import lotto.model.exception.PaymentNotMultipleOfThousandException;
import lotto.model.exception.PaymentNotNaturalNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentCreatorTest {
        @Test
        void 지불_금액_생성_검사() {
                assertThat(PaymentCreator.create(1000).getNumber()).isEqualTo(1000);
        }

        @Test
        void 지불_금액_양수_검사() {
                assertThrows(PaymentNotNaturalNumberException.class, ()->{
                        PaymentCreator.create(-1);
                });
        }

        @Test
        void 지불_금액_1000_배수_검사() {
                assertThrows(PaymentNotMultipleOfThousandException.class, ()->{
                        PaymentCreator.create(1500);
                });
        }
}
