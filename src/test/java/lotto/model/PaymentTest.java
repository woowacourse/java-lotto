package lotto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentTest {
        @Test
        void 구입금액_검사_0이하() {
                assertThrows(PaymentNotNaturalNumberException.class, () -> {
                        new Payment(0);
                });
        }

        @Test
        void 구입금액_검사_1000배수() {
                assertThrows(PaymentNotMultipleOfThousandException.class, () -> {
                        new Payment(1001);
                });
        }
}
