package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    private WinningLotto winningLotto;

    @DisplayName("당첨로또 생성 정상 테스트")
    @Test
    void winningLottoTest() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6});
        winningLotto = new WinningLotto(winningNumbers, new LottoNumber(7));
        assertThat(winningLotto).isInstanceOf(WinningLotto.class);
    }

    @DisplayName("당첨로또 생성 보너스번호 중복 테스트")
    @Test
    void duplicatedWinningLottoTest() {
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
