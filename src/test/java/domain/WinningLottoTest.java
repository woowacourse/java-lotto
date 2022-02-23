package domain;

<<<<<<< HEAD
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.LottoGenerator;
import domain.LottoGenerator.ManualLottoGenerator;
=======
>>>>>>> 01f73d4 (feat: Winning Lotto 당첨번호와 보너스볼 간 중복검사)
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("보너스볼과 당첨번호의 중복을 검사한 뒤, 중복되면 예외를 발생시킨다.")
    void validateDuplicate() {
<<<<<<< HEAD
        LottoGenerator lottoGenerator = new ManualLottoGenerator();
        List<Integer> winningNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            winningNumbers.add(i);
        }
        LottoNumber bonusBall = new LottoNumber(1);
        assertThatThrownBy(() -> new WinningLotto(lottoGenerator.generateLotto(winningNumbers), bonusBall))
=======
        List<LottoNumber> winningNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            winningNumbers.add(new LottoNumber(i));
        }
        LottoNumber bonusBall = new LottoNumber(1);
        assertThatThrownBy(() -> new WinningLotto(winningNumbers,bonusBall))
>>>>>>> 01f73d4 (feat: Winning Lotto 당첨번호와 보너스볼 간 중복검사)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.LOTTO_AND_BONUS_BALL_DUPLICATION);
    }

}