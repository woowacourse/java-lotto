package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    private WinningLotto winningLotto;

    @Test
    void 당첨로또_생성_테스트_정상() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6});
        winningLotto = new WinningLotto(winningNumbers, new LottoNumber(7));
        assertThat(winningLotto).isInstanceOf(WinningLotto.class);
    }

    @Test
    void 당첨로또_생성_테스트_보너스번호_중복() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6});
        assertThatThrownBy(() ->
                winningLotto = new WinningLotto(winningNumbers, new LottoNumber(1)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    private LottoNumbers makeLottoNumbers(int[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int num : numbers) {
            lottoNumbers.add(new LottoNumber(num));
        }
        return new LottoNumbers(lottoNumbers);
    }
}
