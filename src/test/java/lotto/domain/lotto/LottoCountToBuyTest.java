package lotto.domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LottoCountToBuyTest {
    @Nested
    @DisplayName("생성하는 기능은")
    class Of {
        @Nested
        @DisplayName("돈과 수동 로또 개수가 주어지면")
        class Context_with_money_and_manual_count {
            @Test
            @DisplayName("수동과 자동 개수를 가진 객체를 생성한다.")
            void it_create() {
                LottoCountToBuy lottoCountToBuy = LottoCountToBuy.of(new Money(3000), new Count(1));
                Assertions.assertThat(lottoCountToBuy.getManualCount()).isEqualTo(1);
                Assertions.assertThat(lottoCountToBuy.getAutoCount()).isEqualTo(2);
            }
        }
    }
}
