package lotto.model;

import lotto.exception.NotSixNumbersException;
import lotto.exception.OverRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {
    private LottoNumber one = LottoNumber.lottoNumber(1);
    private LottoNumber two = LottoNumber.lottoNumber(2);
    private LottoNumber three = LottoNumber.lottoNumber(3);
    private LottoNumber four = LottoNumber.lottoNumber(4);
    private LottoNumber five = LottoNumber.lottoNumber(5);
    private LottoNumber six = LottoNumber.lottoNumber(6);
    private LottoNumber seven = LottoNumber.lottoNumber(7);

    @Test
    @DisplayName("로또 티켓의 숫자가 6개가 아닌 경우")
    void checkLottoLengthTest() {
        assertThatThrownBy(() -> {
            List<LottoNumber> lottoTicket = Arrays.asList(one, two, three, four, five);
            new LottoTicket(lottoTicket);
        }).isInstanceOf(NotSixNumbersException.class)
        .hasMessage("6개의 숫자를 입력하셔야 합니다.");

        assertThatThrownBy(() -> {
            List<LottoNumber> lottoTicket = Arrays.asList(one, two, three, four, five, six, seven);
            new LottoTicket(lottoTicket);
        }).isInstanceOf(NotSixNumbersException.class)
        .hasMessage("6개의 숫자를 입력하셔야 합니다.");
    }

    @Test
    @DisplayName("로또 번호가 null 일 때")
    void checkNullLottoTicket() {
        assertThatThrownBy(() -> {
            new LottoTicket(null);
        }).isInstanceOf(NullPointerException.class)
        .hasMessage("로또의 번호가 null입니다.");
    }
}
