package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import lotto.TestLottoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTicketTest {

    @DisplayName("로또 티켓이 정상적으로 생성되는지 확인")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void createLottoTicket(int number) {
        // given
        List<LottoNumber> numbers = TestLottoFactory.getNumbers(1, 2, 3, 4, 5, 6);
        // when
        LottoTicket lottoTicket = new LottoTicket(numbers);
        // then
        assertThat(lottoTicket.isMatch(new LottoNumber(number))).isTrue();
    }

    @Test
    @DisplayName("길이가 정상이 아니면 예외처리")
    void validateLength() {
        // given
        List<LottoNumber> numbers = TestLottoFactory.getNumbers(1, 2, 3, 4, 5);
        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new LottoTicket(numbers));
    }

    @Test
    @DisplayName("로또 번호는 중복될 수 없다.")
    void validateDistinct() {
        // given
        List<LottoNumber> numbers = TestLottoFactory.getNumbers(1, 2, 3, 4, 5, 5);
        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new LottoTicket(numbers));
    }

    @Test
    @DisplayName("로또 번호는 null일 수 없다.")
    void validateNull() {
        // given
        List<LottoNumber> numbers = null;
        // then
        assertThatThrownBy(() -> new LottoTicket(numbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("로또 번호는 null일 수 없습니다.");
    }

    @Test
    @DisplayName("로또 티켓사이에 같은 숫자를 가진 로또 번호 개수가 정확한지 확인")
    void countMatchByLottoTicket() {
        // when
        List<LottoNumber> numbers = TestLottoFactory.getNumbers(1, 2, 3, 4, 5, 6);
        List<LottoNumber> otherNumbers = TestLottoFactory.getNumbers(1, 2, 3, 7, 8, 9);
        LottoTicket lottoTicket = new LottoTicket(numbers);
        LottoTicket otherTicket = new LottoTicket(otherNumbers);
        // then
        assertThat(lottoTicket.countMatch(otherTicket)).isEqualTo(3);
    }
}
