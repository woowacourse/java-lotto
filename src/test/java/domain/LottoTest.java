package domain;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.LottoGenerator;
<<<<<<< HEAD
import domain.LottoGenerator.ManualLottoGenerator;
=======
import domain.LottoGenerator.WinningLottoGenerator;
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
import org.junit.jupiter.api.BeforeEach;
=======
>>>>>>> 20a623d (feat: 로또 구매 기능 구현)
=======
import org.junit.jupiter.api.BeforeEach;
>>>>>>> ed345ef (feat: 보너스볼 판정 로직 및 Result 객체 구현)
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
<<<<<<< HEAD
<<<<<<< HEAD
import utils.ExceptionMessage;
=======
>>>>>>> 20a623d (feat: 로또 구매 기능 구현)
=======
import utils.ExceptionMessage;
>>>>>>> d722001 (refactor: 예외 메세지 별도 클래스로 분리)

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
<<<<<<< HEAD
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    private List<LottoNumber> lottoNumbers;
    private Lotto lotto;
    private List<Integer> winningNumbers;
    private LottoGenerator lottoGenerator;
<<<<<<< HEAD

    @BeforeEach
    void setUp() {
        lottoGenerator = new ManualLottoGenerator();

        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        lotto = new Lotto(lottoNumbers);

        winningNumbers = new ArrayList<>();
        for (int i = 2; i <= 7; i++) {
            winningNumbers.add(i);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 7})
=======
=======
import static org.assertj.core.api.Assertions.assertThat;
>>>>>>> 6996318 (feat: 로또 판정 로직 및 전체 판정 구현)
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    private List<LottoNumber> lottoNumbers;
    private Lotto lotto;
    private List<LottoNumber> winningNumbers;
=======
>>>>>>> 5b2a52c (refactor: 로또 생성 기능 인터페이스로 분리)

    @BeforeEach
    void setUp() {
        lottoGenerator = new WinningLottoGenerator();

        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        lotto = new Lotto(lottoNumbers);

        winningNumbers = new ArrayList<>();
        for (int i = 2; i <= 7; i++) {
            winningNumbers.add(i);
        }
    }

    @ParameterizedTest
<<<<<<< HEAD
    @ValueSource(ints = {5,7})
>>>>>>> 20a623d (feat: 로또 구매 기능 구현)
=======
    @ValueSource(ints = {5, 7})
>>>>>>> 94c4d43 (style: 코드 포멧팅)
    @DisplayName("Lotto의 size가 6이 아닐 경우 예외를 발생한다.")
    public void lotto_사이즈_5일경우(int lastIndex) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= lastIndex; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
<<<<<<< HEAD
<<<<<<< HEAD
                .hasMessage(ExceptionMessage.LOTTO_SIZE_IS_NOT_SIX);
    }

    @Test
    @DisplayName("Lotto의 숫자들과 당첨숫자를 비교하여 결과를 반환한다.")
    void judge_보너스볼_불일치() {
        WinningLotto winningLotto = new WinningLotto(lottoGenerator.generateLotto(winningNumbers), new LottoNumber(10));
        Result actual = lotto.judge(winningLotto);
        Result expected = new Result(5, false);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Lotto의 숫자들과 당첨숫자를 비교하여 결과를 반환한다.")
    void judge_보너스볼_일치() {
        WinningLotto winningLotto = new WinningLotto(lottoGenerator.generateLotto(winningNumbers), new LottoNumber(1));
        Result actual = lotto.judge(winningLotto);
        Result expected = new Result(5, true);
        assertThat(actual).isEqualTo(expected);
=======
                .hasMessage(Lotto.LOTTO_SIZE_IS_NOT_SIX);
>>>>>>> 20a623d (feat: 로또 구매 기능 구현)
=======
                .hasMessage(ExceptionMessage.LOTTO_SIZE_IS_NOT_SIX);
>>>>>>> d722001 (refactor: 예외 메세지 별도 클래스로 분리)
    }

    @Test
    @DisplayName("Lotto의 숫자들과 당첨숫자를 비교하여 결과를 반환한다.")
    void judge_보너스볼_불일치() {
        WinningLotto winningLotto = new WinningLotto(lottoGenerator.generateLotto(winningNumbers), new LottoNumber(10));
        Result actual = lotto.judge(winningLotto);
        Result expected = new Result(5, false);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Lotto의 숫자들과 당첨숫자를 비교하여 결과를 반환한다.")
    void judge_보너스볼_일치() {
        WinningLotto winningLotto = new WinningLotto(lottoGenerator.generateLotto(winningNumbers), new LottoNumber(1));
        Result actual = lotto.judge(winningLotto);
        Result expected = new Result(5, true);
        assertThat(actual).isEqualTo(expected);
    }
}
