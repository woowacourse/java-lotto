package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    @Test
    @DisplayName("로또 생성하기")
    void createLotto() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoTicket lottoTicket = new LottoTicket(numbers);
        assertThat(lottoTicket).isEqualTo(new LottoTicket(numbers));
    }

    @DisplayName("티켓에 찾는 번호가 있으면 참을 반환한다.")
    @Test
    public void contain() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber number = new LottoNumber(1);
        LottoNumber excludedNumber = new LottoNumber(8);
        assertThat(lottoTicket.hasNumber(number)).isTrue();
        assertThat(lottoTicket.hasNumber(excludedNumber)).isFalse();
    }
}
