package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoFactoryTest {

    private static final LottoFactory lottoFactory = new LottoFactory();

    @Test
    @DisplayName("입력한 수량 만큼 수동와 자동 로또를 발급한다")
    void generateManualLotto() {
        final List<List<Integer>> manualLottoNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(7, 8, 9, 10, 11, 12),
                Arrays.asList(1, 2, 3, 4, 5, 7)
        );
        final int autoLottoQuantity = 7;
        final int expectedTotalQuantity = manualLottoNumbers.size() + autoLottoQuantity;

        final List<Lotto> lottoTicket = lottoFactory.generateLottoTicket(manualLottoNumbers, autoLottoQuantity);
        final int actual = lottoTicket.size();

        assertThat(actual).isEqualTo(expectedTotalQuantity);
    }
}
