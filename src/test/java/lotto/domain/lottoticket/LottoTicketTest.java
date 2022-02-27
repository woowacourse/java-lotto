package lotto.domain.lottoticket;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {
    @Test
    @DisplayName("중복된 숫자로 생성시 예외 발생")
    void hasDuplication() {
        assertThatThrownBy(() -> new LottoTicket("2,2,3,4,5,6")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 중복이 불가능합니다.");
    }

    @Test
    @DisplayName("문자열이 포함된 경우 예외 발생")
    void hasString() {
        assertThatThrownBy(() -> new LottoTicket("1,aa,3,4,5,6")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("숫자가 6개 미만인 경우 예외 발생")
    void invalidCount() {
        assertThatThrownBy(() -> new LottoTicket("2,5,6")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 개수는 6여야 합니다.");
    }

    @Test
    @DisplayName("중복된 숫자가 없는 경우 테스트 통과")
    void validInput() {
        assertThatNoException().isThrownBy(() -> new LottoTicket("1,2,3,4,5,6"));
    }

    @Test
    @DisplayName("공백이 포함된 경우 테스트 통과")
    void hasBlank() {
        assertThatNoException().isThrownBy(() -> new LottoTicket("1,  8  ,3,4 ,5 ,6"));
    }
}
