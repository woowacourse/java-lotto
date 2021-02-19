package lotto.domain.lottos.winnerlotto;

import lotto.domain.lottos.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoWinnerTicketTest {

    @Test
    @DisplayName("로또 티켓을 생성한다.")
    public void createLottoTicketTest() {
        List<LottoNumber> lottoWinnerNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );

        assertThat(new LottoWinnerTicket(lottoWinnerNumbers)).isInstanceOf(LottoWinnerTicket.class);
    }

    @Test
    @DisplayName("중복된 숫자가 있는지 검사")
    public void duplicateNumberTest() {
        List<LottoNumber> lottoWinnerNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(5)
        );

        assertThatThrownBy(() -> {
            new LottoWinnerTicket(lottoWinnerNumbers);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("숫자가 6개를 초과하면 예외발생 검사")
    public void numberCountGreaterThanSixThrowsExceptionTest() {
        List<LottoNumber> lottoWinnerNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(45)
        );

        assertThatThrownBy(() -> {
            new LottoWinnerTicket(lottoWinnerNumbers);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("숫자가 6개미만이면 예외발생 검사")
    public void numberCountLessThanSixThrowsExceptionTest() {
        List<LottoNumber> lottoWinnerNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
        );

        assertThatThrownBy(() -> {
            new LottoWinnerTicket(lottoWinnerNumbers);
        }).isInstanceOf(RuntimeException.class);
    }
}
