package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    private WinningLotto winningLotto;

    @Test
    void 당첨로또_생성_테스트_정상() {
        List<LottoNumber> winningNumbers = Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(5),new LottoNumber(6));
        winningLotto = new WinningLotto(winningNumbers, new BonusNumber(7));
        assertThat(winningLotto).isInstanceOf(WinningLotto.class);
    }

    @Test
    void 당첨로또_생성_테스트_길이() {
        List<LottoNumber> winningNumbers = Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(5));
        assertThatThrownBy(() ->
                winningLotto = new WinningLotto(winningNumbers, new BonusNumber(7)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 당첨로또_생성_테스트_중복() {
        List<LottoNumber> winningNumbers = Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(4),new LottoNumber(6));
        assertThatThrownBy(() ->
                winningLotto = new WinningLotto(winningNumbers, new BonusNumber(7)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 당첨로또_생성_테스트_보너스번호_중복() {
        List<LottoNumber> winningNumbers = Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(5),new LottoNumber(6));
        assertThatThrownBy(() ->
                winningLotto = new WinningLotto(winningNumbers, new BonusNumber(1)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }
}
