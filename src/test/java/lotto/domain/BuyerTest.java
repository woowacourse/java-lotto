package lotto.domain;

import lotto.domain.lottoTicket.LottoAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class BuyerTest {
    List<String> manualLottoNumbers;

    @BeforeEach
    void setUp() {
        manualLottoNumbers =
                Arrays.asList(
                        "1,2,3,4,5,6",
                        "1,2,3,4,5,7",
                        "1,2,3,6,8,9"
                );
    }

    @Test
    @DisplayName("수동 생성 테스트")
    void initManualTest() {
        LottoAmount lottoAmount = new LottoAmount(3, 3);

        assertThatCode(() -> new Buyer(manualLottoNumbers, lottoAmount))
                .doesNotThrowAnyException();
        assertThat(new Buyer(manualLottoNumbers, lottoAmount).getLottos().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("자동 생성 테스트")
    void initAutoTest() {
        LottoAmount lottoAmount = new LottoAmount(3, 0);

        assertThatCode(() -> new Buyer(null, lottoAmount))
                .doesNotThrowAnyException();
        assertThat(new Buyer(null, lottoAmount).getLottos().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("수동 + 자동 생성 테스트")
    void initBothTest() {
        LottoAmount lottoAmount = new LottoAmount(6, 3);

        assertThatCode(() -> new Buyer(manualLottoNumbers, lottoAmount))
                .doesNotThrowAnyException();
        assertThat(new Buyer(manualLottoNumbers, lottoAmount).getLottos().size()).isEqualTo(6);
    }
}
