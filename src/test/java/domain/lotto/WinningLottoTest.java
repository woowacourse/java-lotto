package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.lotto.BonusNumDuplicatedException;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class WinningLottoTest {
    private WinningLotto winningLotto;

    @BeforeEach
    void 당첨번호_생성() {
        winningLotto = LottoFactory.createWinNums(Arrays.asList(1, 2, 3, 4, 5, 6), 10);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void 로또_숫자_확인(int input) {
        assertThat(winningLotto.contains(LottoNumber.getInstance(input)))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10})
    void 로또_숫자_포함안될때_검사(int input) {
        assertThat(winningLotto.contains(LottoNumber.getInstance(input)))
                .isFalse();
    }

    @Test
    void 로또_번호와_보너스_중복_확인() {
        assertThatThrownBy(() -> LottoFactory.createWinNums(Arrays.asList(1, 2, 3, 4, 5, 6), 5))
                .isInstanceOf(BonusNumDuplicatedException.class)
                .hasMessage("보너스 번호는 로또 번호와 중복될 수 없습니다. : 5");
    }
}