package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoCountTest {

    @ParameterizedTest
    @CsvSource(value = {"0:0", "1:1", "0:1", "1:0", "3:0"}, delimiter = ':')
    @DisplayName("유효한 로또 갯수 객체를 만드는 경우")
    void crateValidLottoCount(int manualLottoCount, int autoLottoCount) {
        LottoCount lottoCount = new LottoCount(manualLottoCount, autoLottoCount);

        assertThat(lottoCount).isNotNull();
    }

    @ParameterizedTest
    @CsvSource(value = {"-1:1", "-1:-1", "0:-1", "2:-5"}, delimiter = ':')
    @DisplayName("유효하지 않은 로또 갯수 객체를 만드는 경우")
    void crateInvalidLottoCount(int manualLottoCount, int autoLottoCount) {
        assertThatThrownBy(() -> new LottoCount(manualLottoCount, autoLottoCount))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
