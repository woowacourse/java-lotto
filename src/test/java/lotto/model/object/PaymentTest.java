package lotto.model.object;

<<<<<<< HEAD
import lotto.model.Payment;
=======
>>>>>>> da7b5280162c03e3cb85956a36ace4e4eaa36719
import lotto.model.exception.PaymentNotMultipleOfThousandException;
import lotto.model.exception.PaymentNotNaturalNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentTest {
        @Test
        void 구입_금액_자연수_검사() {
                assertThrows(PaymentNotNaturalNumberException.class, ()->{
                        new Payment(-1);
                });
        }

        @Test
        void 구입_금액_1000_배수_검사() {
                assertThrows(PaymentNotMultipleOfThousandException.class, ()->{
                        new Payment(5050);
                });
        }

        @Test
        void 구매_가능_로또_숫자_검사() {
                assertThat(new Payment(5000).isPurchasable(6)).isEqualTo(false);
        }
}