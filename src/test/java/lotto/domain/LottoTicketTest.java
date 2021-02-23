package lotto.domain;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {
    @Test
    @DisplayName("매개변수로 넘어온 리스트의 길이가 6을 넘을 경우")
    void checkMemberNames() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(8),
                LottoNumber.valueOf(9),
                LottoNumber.valueOf(10),
                LottoNumber.valueOf(40));
        assertThatThrownBy(() -> {
            LottoTicket lottoTicket = new LottoTicket(numbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("매개변수로 넘어온 리스트의 길이가 6이 되지 않는 경우")
    void wrongArgumentCreate() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(8),
                LottoNumber.valueOf(9));
        assertThatThrownBy(() -> {
            LottoTicket lottoTicket = new LottoTicket(numbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("매개변수로 넘어온 리스트 안에 중복되는 수가 있는 검사")
    void duplicateNumbers() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(8),
                LottoNumber.valueOf(10),
                LottoNumber.valueOf(10));
        assertThatThrownBy(() -> {
            LottoTicket lottoTicket = new LottoTicket(numbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
