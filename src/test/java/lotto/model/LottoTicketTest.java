package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import lotto.model.ticket.LottoTicket;
import lotto.model.ticket.number.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    @Test
    @DisplayName("로또 티켓이 정상적으로 생성되는지 확인")
    void createLottoTicket() {
        // given
        List<LottoNumber> numbers = new ArrayList<>(
            List.of(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3),
                    LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(6))
        );
        // when
        LottoTicket lottoTicket = new LottoTicket(numbers);
        // then
        assertThat(lottoTicket).isNotNull();
    }

    @Test
    @DisplayName("길이가 정상이 아니면 예외처리")
    void validateLength() {
        // given
        List<LottoNumber> numbers = new ArrayList<>(
            List.of(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3),
                    LottoNumber.from(4), LottoNumber.from(5))
        );
        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new LottoTicket(numbers));
    }

    @Test
    @DisplayName("로또 번호는 중복될 수 없다.")
    void validateDistinct() {
        // given
        List<LottoNumber> numbers = new ArrayList<>(
            List.of(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3),
                    LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(5))
        );
        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new LottoTicket(numbers));
    }
}
