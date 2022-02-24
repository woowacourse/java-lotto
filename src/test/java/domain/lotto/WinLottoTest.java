package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class WinLottoTest {
    private WinLotto winLottoNumbers;

    @BeforeEach
    void 당첨번호_생성() {
        winLottoNumbers = LottoFactory.createWinLotto(Arrays.asList(1, 2, 3, 4, 5, 6), LottoBall.from(10));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void 로또_숫자_확인(int input) {
        assertThat(winLottoNumbers.isIn(LottoBall.from(input)))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10})
    void 로또_숫자_포함안될때_검사(int input) {
        assertThat(winLottoNumbers.isIn(LottoBall.from(input)))
                .isFalse();
    }
}