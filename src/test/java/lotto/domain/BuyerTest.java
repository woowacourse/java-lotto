package lotto.domain;

import lotto.domain.lottoTicket.LottoAmount;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

public class BuyerTest {
    @Test
    void initTest() {
        List<String> manualLottoNumbers =
                Arrays.asList(
                        "1,2,3,4,5,6",
                        "1,2,3,4,5,7",
                        "1,2,3,6,8,9"
                );

        LottoAmount lottoAmount = new LottoAmount(10, 3);

        assertThatCode(() -> new Buyer(manualLottoNumbers, lottoAmount))
                .doesNotThrowAnyException();
    }
}
