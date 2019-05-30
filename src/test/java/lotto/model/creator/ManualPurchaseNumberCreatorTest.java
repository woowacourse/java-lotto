package lotto.model.creator;

import lotto.model.exception.InvalidManualPurchaseNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManualPurchaseNumberCreatorTest {
        @Test
        void 수동_구매_개수_생성_검사() {
                assertThat(ManualPurchaseNumberCreator.create(5, PaymentCreator.create(15000)).getNumber()).isEqualTo(5);
        }

        @Test
        void 수동_구매_개수_입력_범위_검사() {
                assertThrows(InvalidManualPurchaseNumberException.class, () -> {
                        ManualPurchaseNumberCreator.create(16, PaymentCreator.create(15000));
                });
        }
}
