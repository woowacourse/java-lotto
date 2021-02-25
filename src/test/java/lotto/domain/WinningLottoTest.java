package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    List<Integer> numbers;
    LottoTicket winningTicket;

    @BeforeEach
    void init() {
        numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        winningTicket = new LottoTicket(numbers);
    }

    @DisplayName("구입한 로또에 보너스볼 번호가 있으면 참을 반환한다.")
    @Test
    void checkBonus() {
        List<Integer> ticketNumbers = Arrays.asList(1, 2, 3, 4, 5, 7);
        LottoTicket lottoTicket = new LottoTicket(ticketNumbers);
        WinningLotto winningLotto = new WinningLotto(winningTicket, new LottoNumber(7));
        WinningLotto winningLotto2 = new WinningLotto(winningTicket, new LottoNumber(10));
        assertThat(winningLotto.hasBonus(lottoTicket)).isTrue();
        assertThat(winningLotto2.hasBonus(lottoTicket)).isFalse();
    }

    @DisplayName("당첨 로또는 보너스볼과 숫자가 중복되어선 안된다.")
    @Test
    void duplicate() {
        LottoNumber bonusBall = new LottoNumber(3);
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new WinningLotto(winningTicket, bonusBall));
    }

}