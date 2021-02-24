package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.WinningLotto;
import lotto.model.LottoRank;
import lotto.model.LottoResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private Lottos lottos;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        Lotto lotto1 = Lotto.of(Arrays.asList(8, 21, 23, 41, 42, 43));
        Lotto lotto2 = Lotto.of(Arrays.asList(3, 5, 11, 16, 32, 38));
        Lotto lotto3 = Lotto.of(Arrays.asList(7, 11, 16, 35, 36, 44));
        Lotto lotto4 = Lotto.of(Arrays.asList(1, 8, 11, 31, 41, 42));
        Lotto lotto5 = Lotto.of(Arrays.asList(13, 14, 16, 38, 42, 45));
        Lotto lotto6 = Lotto.of(Arrays.asList(7, 11, 30, 40, 42, 43));
        Lotto lotto7 = Lotto.of(Arrays.asList(2, 13, 22, 32, 38, 45));
        Lotto lotto8 = Lotto.of(Arrays.asList(23, 25, 33, 36, 39, 41));
        Lotto lotto9 = Lotto.of(Arrays.asList(1, 3, 5, 14, 22, 45));
        Lotto lotto10 = Lotto.of(Arrays.asList(5, 9, 38, 41, 43, 44));
        Lotto lotto11 = Lotto.of(Arrays.asList(2, 8, 9, 18, 19, 21));
        Lotto lotto12 = Lotto.of(Arrays.asList(13, 14, 18, 21, 23, 35));
        Lotto lotto13 = Lotto.of(Arrays.asList(17, 21, 29, 37, 42, 45));
        Lotto lotto14 = Lotto.of(Arrays.asList(3, 8, 27, 30, 35, 44));

        List<Lotto> lottosValue = Arrays.asList(lotto1, lotto2, lotto3, lotto4, lotto5,
            lotto6, lotto7, lotto8, lotto9, lotto10,
            lotto11, lotto12, lotto13, lotto14);
        lottos = Lottos.from(lottosValue);
        winningLotto = new WinningLotto(Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)), 7);
    }

    @Test
    void 당첨_확인() {
        // given, when
        LottoResult lottoResult = lottos.match(winningLotto);

        // then
        assertThat(lottoResult.getResult().get(LottoRank.FIFTH)).isEqualTo(1);
    }

    @Test
    void 수익률_계산() {
        // given, when
        LottoResult lottoResult = lottos.match(winningLotto);

        // then
        assertThat(lottoResult.getEarningsRate()).isEqualTo(0.35714285714285715);
    }

    @Test
    public void match_1등() {
        Lotto userLotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        int result = userLotto.match(winningLotto);
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void match_3등() {
        Lotto userLotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 7));

        int result = userLotto.match(winningLotto);

        assertThat(result).isEqualTo(5);
    }

    @Test
    public void of_중복_값() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 5));
        });
    }

    @Test
    public void of_6개_미만의_값() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Lotto.of(Arrays.asList(1, 2, 3, 4, 5));
        });
    }

    @Test
    public void of_문자열_isNull() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 5));
        });
    }
}
