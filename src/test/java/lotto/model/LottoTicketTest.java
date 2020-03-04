package lotto.model;

import lotto.exception.NotSixNumbersException;
import lotto.exception.OverRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {
    private LottoNumber one = new LottoNumber(1);
    private LottoNumber two = new LottoNumber(2);
    private LottoNumber three = new LottoNumber(3);
    private LottoNumber four = new LottoNumber(4);
    private LottoNumber five = new LottoNumber(5);
    private LottoNumber six = new LottoNumber(6);
    private LottoNumber seven = new LottoNumber(7);

    @Test
    @DisplayName("로또 티켓의 숫자가 6개가 아닌 경우")
    void checkLottoLengthTest() {
        assertThatThrownBy(() -> {
            List<LottoNumber> lottoTicket = Arrays.asList(one, two, three, four, five);
            new LottoTicket(lottoTicket);
        }).isInstanceOf(NotSixNumbersException.class);

        assertThatThrownBy(() -> {
            List<LottoNumber> lottoTicket = Arrays.asList(one, two, three, four, five, six, seven);
            new LottoTicket(lottoTicket);
        }).isInstanceOf(NotSixNumbersException.class);
    }
}
