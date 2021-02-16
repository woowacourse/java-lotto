package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {

    @Test
    @DisplayName("로또 티켓을 생성한다.")
    public void createLottoTicketTest() {
        List<LottoNumber> lottoNumbers1 = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );
        List<LottoNumber> lottoNumbers2 = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );

        LottoTicket lottoTicket = new LottoTicket(lottoNumbers1);
        assertThat(lottoTicket).isEqualTo(new LottoTicket(lottoNumbers2));
    }

    @Test
    @DisplayName("중복된 숫자가 있는지 검사")
    public void duplicateNumberTest() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(5)
        );
        assertThatThrownBy(() -> {
            new LottoTicket(lottoNumbers);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("숫자가 6개를 초과하면 예외발생 검사")
    public void numberCountGreaterThanSixThrowsExceptionTest() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(45)
        );
        assertThatThrownBy(() -> {
            new LottoTicket(lottoNumbers);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("숫자가 6개미만이면 예외발생 검사")
    public void numberCountLessThanSixThrowsExceptionTest() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
        );
        assertThatThrownBy(() -> {
            new LottoTicket(lottoNumbers);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("로또 티켓 생성시 입력받은 List가 잘 정렬 되있는지 검사")
    public void lottoTicketSortTest() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(3),
                new LottoNumber(2),
                new LottoNumber(5),
                new LottoNumber(4),
                new LottoNumber(1),
                new LottoNumber(44)
        );
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        assertThat(lottoTicket.getLottoNumbers()).isEqualTo(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(44)
        ));
    }
}
