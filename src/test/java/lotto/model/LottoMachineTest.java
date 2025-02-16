package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoMachineTest {

    private final LottoMachine lottoMachine = new LottoMachine(new TestNumberPicker());

    @DisplayName("정상적인 경우")
    @Nested
    class SuccessCase {

        @DisplayName("전달받은 개수만큼 로또를 발급한다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 10})
        void countOfIssuedLottoNumbers(int count) {
            LottoTicket lottoTicket = lottoMachine.issueLottoTicket(count);

            assertThat(lottoTicket.getLottoCount())
                    .isEqualTo(count);
        }
    }

    @DisplayName("예외가 발생하는 경우")
    @Nested
    class ExceptionCase {

        @DisplayName("번호 생성기가 null인 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenNumberPickerIsNull() {
            assertThatThrownBy(() -> new LottoMachine(null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("번호 생성기는 null이 될 수 없습니다.");
        }

        @DisplayName("로또를 0개 이하로 발급하려고 할 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {0, -1})
        void shouldThrowException_WhenCountIsLessThanOne(int count) {
            assertThatThrownBy(() -> lottoMachine.issueLottoTicket(count))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또는 최소 1개 이상 발급할 수 있습니다.");
        }
    }
}
