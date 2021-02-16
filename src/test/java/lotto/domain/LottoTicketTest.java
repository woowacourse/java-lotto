package lotto.domain;

import lotto.exception.IllegalLottoTicketSizeError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {
    @Test
    @DisplayName("로또 티켓 생성 시 숫자가 6개인지 확인")
    void checkMemberNames() {
        List<Integer> numbers = Arrays.asList(1, 4, 5, 8, 10, 9, 40);
        LottoTicket lottoTicket = new LottoTicket(numbers);
        assertThat(lottoTicket.lottoTicket().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("매개변수로 넘어온 리스트의 길이가 6이 되지 않는 경우")
    void wrongArgumentCreate() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 5);
        assertThatThrownBy(()-> {
            LottoTicket lottoTicket = new LottoTicket(numbers);
        }).isInstanceOf(IllegalLottoTicketSizeError.class);
    }
}
