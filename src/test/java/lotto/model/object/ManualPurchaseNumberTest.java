package lotto.model.object;

import lotto.model.exception.InvalidManualPurchaseNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ManualPurchaseNumberTest {
        Payment payment;

        @BeforeEach
        void setUp() {
                payment = new Payment(5000);
        }

        @Test
        void 수동_구매_로또_범위_검사() {
                assertThrows(InvalidManualPurchaseNumberException.class, ()->{
                        new ManualPurchaseNumber(6, payment);
                });
        }
}