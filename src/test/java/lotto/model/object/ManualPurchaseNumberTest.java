package lotto.model.object;

<<<<<<< HEAD
import lotto.model.ManualPurchaseNumber;
import lotto.model.Payment;
=======
>>>>>>> da7b5280162c03e3cb85956a36ace4e4eaa36719
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