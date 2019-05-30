package lotto.model.creator;

import lotto.model.exception.InvalidManualPaymentNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManualPaymentNumberCreatorTest {
        @Test
        void 수동_구매_개수_생성_검사() {
                assertThat(ManualPaymentNumberCreator.create(5, PaymentCreator.create(15000)).getNumber()).isEqualTo(5);
        }

        @Test
        void 수동_구매_개수_입력_범위_검사() {
                assertThrows(InvalidManualPaymentNumberException.class, () -> {
                        ManualPaymentNumberCreator.create(16, PaymentCreator.create(15000));
                });
        }
}
