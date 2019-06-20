package lotto.domain;

import lotto.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class InputValidatorTest {
    Lotto lotto;
    Money money;

    @BeforeEach
    void setUp() {
        lotto = new Lotto(LottoNumber.getLotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        money = new Money(5000);
    }

    @Test
    void 로또가격_공백() {
        assertThat(InputValidator.isNotValidPrice(" ")).isTrue();
    }

    @Test
    void 로또가격_음수() {
        assertThat(InputValidator.isNotValidPrice("-1")).isTrue();
    }

    @Test
    void 로또가격_천원단위아닐때() {
        assertThat(InputValidator.isNotValidPrice("1100")).isTrue();
    }

    @Test
    void 로또숫자_7개() {
        assertThat(InputValidator.isNotValidLotto(Arrays.asList("1,2,3,4,5,6,7"))).isTrue();
    }

    @Test
    void 로또숫자_음수() {
        assertThat(InputValidator.isNotValidLotto(Arrays.asList("-2,3,4,5,6,7"))).isTrue();
    }

    @Test
    void 로또숫자_45이상() {
        assertThat(InputValidator.isNotValidLotto(Arrays.asList("1,2,3,5,6,46"))).isTrue();
    }

    @Test
    void 로또숫자_공백포함() {
        assertThat(InputValidator.isNotValidLotto(Arrays.asList("1,2,3, ,5,6,7"))).isTrue();
    }

    @Test
    void 보너스볼_공백() {
        assertThat(InputValidator.isNotValidLotto(Arrays.asList("1,2,3, ,5,6,7"))).isTrue();
    }

    @Test
    void 보너스볼_음수() {
        assertThat(InputValidator.isNotValidWinningLotto(lotto, "-1")).isTrue();
    }

    @Test
    void 보너스볼_로또번호와중복() {
        assertThat(InputValidator.isNotValidWinningLotto(lotto, "1")).isTrue();
    }

    @Test
    void 보너스볼_문자() {
        assertThat(InputValidator.isNotValidWinningLotto(lotto, "abs")).isTrue();
        assertThat(InputValidator.isNotValidLotto(Arrays.asList("1,2,3, ,5,6,7"))).isTrue();
    }

    @Test
    void 수동로또개수_문자() {
        assertThat(InputValidator.isNotValidCustomLottoCount("asd", money)).isTrue();
    }

    @Test
    void 수동로또개수_음수() {
        assertThat(InputValidator.isNotValidCustomLottoCount("-1", money)).isTrue();
    }

    @Test
    void 수동로또_돈초과() {
        assertThat(InputValidator.isNotValidCustomLottoCount("6", money)).isTrue();
    }

    @Test
    void 수동로또_음수() {
        String[] lottoInput = new String[1];
        lottoInput[0] = "-1,2,3,4,5,6";

        assertThat(InputValidator.isNotValidCustomLottoes(lottoInput)).isTrue();
    }

    @Test
    void 수동로또_공백() {
        String[] lottoInput = new String[1];
        lottoInput[0] = " ,2,3,4,5,6";

        assertThat(InputValidator.isNotValidCustomLottoes(lottoInput)).isTrue();
    }

    @Test
    void 수동로또_45이상() {
        String[] lottoInput = new String[1];
        lottoInput[0] = "1,2,3,4,5,46";

        assertThat(InputValidator.isNotValidCustomLottoes(lottoInput)).isTrue();
    }


}
