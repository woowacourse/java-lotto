package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {
    @DisplayName("로또 번호 생성을 테스트한다.")
    @Test
    public void createLottoTicketTest() {
        LottoTicket lottoTicket = LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThat(lottoTicket.numbers().containsAll(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ))).isTrue();
    }

    @DisplayName("LottoNumberRepository 를 이용해 랜덤하게 로또 티켓을 생성한다.")
    @Test
    public void createRandomLottoTicketTest() {
        LottoTicket lottoTicket = new LottoTicket(LottoNumberRepository.shuffleLottoNumbers());

        assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
    }

    @DisplayName("입력 값이 6개가 아닌 경우 예외를 발생시킨다.")
    @Test
    public void validateLottoNumberCount() {
        assertThatThrownBy(() -> LottoTicket.valueOf(
                Arrays.asList(1, 2, 3, 4)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 번호를 입력할 경우 예외를 발생시킨다.")
    @Test
    public void validLottoTicketTest() {
        assertThatThrownBy(() -> LottoTicket.valueOf(
                Arrays.asList(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
