package domain;

<<<<<<< HEAD
<<<<<<< HEAD
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.LottoGenerator;
import domain.LottoGenerator.ManualLottoGenerator;
=======
>>>>>>> 01f73d4 (feat: Winning Lotto 당첨번호와 보너스볼 간 중복검사)
=======
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.LottoGenerator;
<<<<<<< HEAD
import domain.LottoGenerator.WinningLottoGenerator;
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
import domain.LottoGenerator.ManualLottoGenerator;
>>>>>>> 27b9569 (refactor : 인터페이스에서 원하는 추상 메서드만 몸체를 구현하고자 어댑터 클래스 추가)
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
<<<<<<< HEAD
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
=======
        LottoGenerator lottoGenerator = new WinningLottoGenerator();
=======
        LottoGenerator lottoGenerator = new ManualLottoGenerator();
>>>>>>> 27b9569 (refactor : 인터페이스에서 원하는 추상 메서드만 몸체를 구현하고자 어댑터 클래스 추가)
        List<Integer> winningNumbers = new ArrayList<>();
>>>>>>> 5b2a52c (refactor: 로또 생성 기능 인터페이스로 분리)
        for (int i = 1; i <= 6; i++) {
            winningNumbers.add(i);
        }
        LottoNumber bonusBall = new LottoNumber(1);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        assertThatThrownBy(() -> new WinningLotto(winningNumbers,bonusBall))
>>>>>>> 01f73d4 (feat: Winning Lotto 당첨번호와 보너스볼 간 중복검사)
=======
        assertThatThrownBy(() -> new WinningLotto(lottoGenerator.generateLotto(winningNumbers),bonusBall))
>>>>>>> 5b2a52c (refactor: 로또 생성 기능 인터페이스로 분리)
=======
        assertThatThrownBy(() -> new WinningLotto(lottoGenerator.generateLotto(winningNumbers), bonusBall))
>>>>>>> 94c4d43 (style: 코드 포멧팅)
=======
        assertThatThrownBy(() -> new WinningLotto(lottoGenerator.generateWinningLotto(winningNumbers), bonusBall))
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)
=======
        assertThatThrownBy(() -> new WinningLotto(lottoGenerator.generateLotto(winningNumbers), bonusBall))
>>>>>>> 27b9569 (refactor : 인터페이스에서 원하는 추상 메서드만 몸체를 구현하고자 어댑터 클래스 추가)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.LOTTO_AND_BONUS_BALL_DUPLICATION);
    }

}