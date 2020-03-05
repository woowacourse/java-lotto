package lotto.model;

import lotto.exception.NotSixNumbersException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTicketTest {
    private LottoNumber one = LottoNumber.valueOf(1);
    private LottoNumber two = LottoNumber.valueOf(2);
    private LottoNumber three = LottoNumber.valueOf(3);
    private LottoNumber four = LottoNumber.valueOf(4);
    private LottoNumber five = LottoNumber.valueOf(5);
    private LottoNumber six = LottoNumber.valueOf(6);
    private LottoNumber seven = LottoNumber.valueOf(7);
    private LottoNumber nine = LottoNumber.valueOf(9);
    private LottoNumber eleven = LottoNumber.valueOf(11);
    private LottoNumber thirteen = LottoNumber.valueOf(13);

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

    @Test
    @DisplayName("로또 티켓과 당첨 번호가 일치하는 갯수 반환")
    void matchNumber() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(one, two, three, four, five, six));
        WinNumber winNumber = new WinNumber(new LottoTicket(
                Arrays.asList(one, three, five, seven, nine, eleven)));

        assertThat(lottoTicket.matchNumber(winNumber)).isEqualTo(3);
    }

    @Test
    @DisplayName("로또 티켓과 보너스 볼이 일치하면 true 반환")
    void matchesWithBonusBall() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(one, two, three, four, five, six));
        WinNumber winNumber = new WinNumber(new LottoTicket(
                Arrays.asList(two, three, five, seven, nine, eleven)));
        BonusBall bonusBall = new BonusBall(winNumber, one);

        assertTrue(lottoTicket.matchesWithBonusBall(bonusBall));

        bonusBall = new BonusBall(winNumber, thirteen);
        assertFalse(lottoTicket.matchesWithBonusBall(bonusBall));
    }

    @Test
    @DisplayName("로또 티켓과 로또 번호가 일치하면 true 반환")
    void matchesWithNumber() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(one, two, three, four, five, six));

        assertFalse(lottoTicket.matchesWithNumber(thirteen));
        assertTrue(lottoTicket.matchesWithNumber(six));
    }
}
