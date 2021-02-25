package lotto.lottogame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.lottoticket.LottoNumber.ERROR_MESSAGE_INVALID_RANGE;
import static lotto.lottoticket.LottoTicketValidation.ERROR_MESSAGE_INVALID_INPUT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualLottoCountTest {
    @Test
    @DisplayName("생성 확인")
    void create() {
        assertThat(new ManualLottoCount("3")).isEqualTo(new ManualLottoCount("3"));
    }

    @ParameterizedTest
    @DisplayName("숫자를 입력하지 않은 경우 예외처리")
    @ValueSource(strings = {"-", "d"})
    void AutoLottoCountNotNumber(String value) {
        assertThatThrownBy(() -> new ManualLottoCount(value)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_INPUT);
    }

    @ParameterizedTest
    @DisplayName("유효한 범위인지 확인")
    @ValueSource(strings = {"6", "-1"})
    void AutoLottoCountInvalidRange(String value) {
        LottoCount lottoCount = new LottoCount(5);
        assertThatThrownBy(() -> new ManualLottoCount(value).validRange(lottoCount)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_RANGE);
    }

    @Test
    @DisplayName("올바른 입력 확인")
    void AutoLottoCountValidInput() {
        LottoCount lottoCount = new LottoCount(9);
        ManualLottoCount manualLottoCount = new ManualLottoCount("5");
        assertThat(manualLottoCount.makeAutoCount(lottoCount)).isEqualTo(new LottoCount(4));
    }
}
