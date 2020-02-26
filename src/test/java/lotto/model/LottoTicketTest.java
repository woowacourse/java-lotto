package lotto.model;

import lotto.exception.NotSixNumbersException;
import lotto.exception.OverRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {

    @Test
    @DisplayName("로또 티켓의 숫자가 6개가 이닌 경우")
    void checkLottoLengthTest() {
        assertThatThrownBy(() -> {
            List<Integer> lottoTicket = Arrays.asList(1,2,3,4,5);
            new LottoTicket(lottoTicket);
        }).isInstanceOf(NotSixNumbersException.class);

        assertThatThrownBy(() -> {
            List<Integer> lottoTicket = Arrays.asList(1,2,3,4,5,6,7);
            new LottoTicket(lottoTicket);
        }).isInstanceOf(NotSixNumbersException.class);
    }

    @Test
    @DisplayName("로또 티켓의 숫자가 1 ~ 45 가 아닌 경우")
    void checkLottoNumberRangeTest() {
        assertThatThrownBy(() -> {
            List<Integer> lottoTicket = Arrays.asList(0,2,3,4,5,6);
            new LottoTicket(lottoTicket);
        }).isInstanceOf(OverRangeException.class);

        assertThatThrownBy(() -> {
            List<Integer> lottoTicket = Arrays.asList(1,2,3,4,5,56);
            new LottoTicket(lottoTicket);
        }).isInstanceOf(OverRangeException.class);
    }
}
