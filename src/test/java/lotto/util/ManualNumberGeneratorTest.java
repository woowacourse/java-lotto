package lotto.util;

import lotto.domain.lottos.LottoNumber;
import lotto.domain.lottos.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static lotto.domain.lottos.LottoTicket.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ManualNumberGeneratorTest {

    private String input = "1,2,3,4,5,6";

    @Test
    @DisplayName("입력 받은 숫자를 생성해서 반환해준다.")
    public void createManualLottoNumbersTest() {
        assertThat(new ManualNumberGenerator(input).generateNumbers())
                .containsExactly(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                );
    }

    @ParameterizedTest(name = "입력값이 null이면 NullPointerException을 발생시킨다.")
    @NullSource
    public void inputIsNullTest(String input) {
        assertThatThrownBy(() -> {
            new ManualNumberGenerator(input).generateNumbers();
        }).isInstanceOf(NullPointerException.class).hasMessage(NULL_ERROR_MESSAGE);
    }

    @ParameterizedTest(name = "입력값이 빈값이면 IllegalArgumentException을 발생시킨다.")
    @EmptySource
    public void inputIsEmptyTest(String input) {
        assertThatThrownBy(() -> {
            new ManualNumberGenerator(input).generateNumbers();
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(EMPTY_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("입력값에 숫자가 아닌 문자(또는 문자열)가 있으면 IllegalArgumentException을 발생시킨다.")
    public void inputIsNotPositiveNumber() {
        assertThatThrownBy(() -> {
            new ManualNumberGenerator("1,이건문자열이다,2,3,4,5").generateNumbers();
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(ManualNumberGenerator.NUMBER_FORMAT_ERROR_MESSAGE, LOTTO_NUMBER_SIZE);
    }

    @Test
    @DisplayName("반환되는 로또 숫자 리스트는 불변 객체라서 값을 추가하려고 하면 예외가 발생한다.")
    public void ManualLottoTicketIsUnmodifiableList() {
        assertThatThrownBy(() -> {
            new ManualNumberGenerator("1,2,3,4,5,6").generateNumbers().add(new LottoNumber(7));
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}