package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.lotto.LottoNumDuplicatedException;
import exception.lotto.LottoNumWrongSizeException;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.NumsGenerator;

@SuppressWarnings("NonAsciiCharacters")
class LottoTest {
    private Lotto lotto;

    @BeforeEach
    void 로또_번호_생성() {
        lotto = Lotto.from(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void 로또_번호_6개_일치_검사() {
        WinNumbers winNumbers = LottoFactory.createWinNums(
                Arrays.asList(1, 2, 3, 4, 5, 6), 10
        );

        int sameNumber = lotto.countSameNum(winNumbers);
        assertThat(sameNumber)
                .isEqualTo(6);
    }

    @Test
    void 로또_보너스_포함_될때_검사() {
        assertThat(lotto.contains(LottoNumber.getInstance(6)))
                .isTrue();
    }

    @Test
    void 로또_보너스_포함_안될때_검사() {
        assertThat(lotto.contains(LottoNumber.getInstance(10)))
                .isFalse();
    }

    @Test
    void 로또_중복_예외() {
        assertThatThrownBy(() -> Lotto.from(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 1))))
                .isInstanceOf(LottoNumDuplicatedException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }

    @Test
    void 로또_숫자6개아님_예외() {
        assertThatThrownBy(() -> Lotto.from(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6, 7))))
                .isInstanceOf(LottoNumWrongSizeException.class)
                .hasMessage("로또 번호는 6개로 이루어져야 합니다.");
    }
}