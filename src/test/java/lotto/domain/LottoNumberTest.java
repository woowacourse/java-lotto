package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exceptions.InvalidLottoNumberException;
import lotto.exceptions.InvalidLottoTicketException;

public class LottoNumberTest {
    @Test
    @DisplayName("당첨 번호 입력이 유효한 경우")
    void find() {
        assertThat(LottoNumber.find("1").getValue()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "46", "abc"})
    @DisplayName("당첨 번호 입력이 유효하지 않은 경우")
    void invalidFindWithNumber(String number) {
        assertThatThrownBy(() -> {
            LottoNumber.find(number);
        }).isInstanceOf(InvalidLottoNumberException.class);
    }

    @Test
    @DisplayName("당첨 번호 중 중복되는 숫자가 있는 경우")
    void numberDuplicate() {
        assertThatThrownBy(() -> {
            new LottoTicket(
                Arrays.asList(LottoNumber.ONE, LottoNumber.ONE, LottoNumber.ONE, LottoNumber.ONE, LottoNumber.ONE,
                    LottoNumber.ONE));
        }).isInstanceOf(InvalidLottoTicketException.class);
    }

}
