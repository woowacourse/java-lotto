package lotto.domain.lottoticket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource(value = {"1:true", "7:false"}, delimiter = ':')
    @DisplayName("로또 티켓이 매개변수의 로또번호를 포함하는지 테스트")
    void isContains(int lottoNumber, boolean expected) {
        final LottoTicket myLottoTicket = new LottoTicket("1,2,3,4,5,6");
        final LottoNumber otherLottoNumber = new LottoNumber(lottoNumber);

        assertThat(myLottoTicket.isContains(otherLottoNumber)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5,6:6", "7,8,9,10,11,12:0"}, delimiter = ':')
    @DisplayName("로또 티켓 비교해서 일치하는 숫자 반환 테스트")
    void calculateSameCount(String lottoNumbers, int expected) {
        final LottoTicket myLottoTicket = new LottoTicket("1,2,3,4,5,6");
        final LottoTicket otherLottoTicket = new LottoTicket(lottoNumbers);

        assertThat(myLottoTicket.calculateSameCount(otherLottoTicket)).isEqualTo(expected);
    }
}
