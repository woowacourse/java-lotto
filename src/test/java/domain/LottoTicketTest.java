package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {
    @DisplayName("로또 티켓 생성을 테스트한다.")
    @Test
    public void createLottoTicketTest() {
        LottoTicket lottoTicket = LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThat(lottoTicket.numbers().containsAll(Arrays.asList(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
        ))).isTrue();
    }

    @DisplayName("LottoNumberRepository를 이용해 랜덤하게 로또 티켓을 생성한다.")
    @Test
    public void createRandomLottoTicketTest() {
        LottoTicket lottoTicket = LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers());
        assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
    }

    @DisplayName("중복된 번호를 입력할 경우 예외를 발생시킨다.")
    @Test
    public void validLottoTicketTest() {
        assertThatThrownBy(() -> LottoTicket.valueOf(
                Arrays.asList(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력 값이 6개가 아닌 경우 예외를 발생시킨다.")
    @Test
    public void validateLottoNumberCount() {
        assertThatThrownBy(() -> LottoTicket.valueOf(
                Arrays.asList(1, 2, 3, 4)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
