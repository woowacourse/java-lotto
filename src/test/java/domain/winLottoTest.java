package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class winLottoTest {

    private static LottoNumbers lottoNumbers;

    @BeforeAll
    static void initLottoNumbers() {
        List<LottoNumber> input = List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::getInstance)
                .collect(Collectors.toList());
        lottoNumbers = new LottoNumbers(input);
    }

    @Test
    @DisplayName("로또 1등 당첨")
    void firstPlaceLottoPrize() {
        List<LottoNumber> input = List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::getInstance)
                .collect(Collectors.toList());

        WinLotto winLotto = new WinLotto(new LottoNumbers(input), LottoNumber.getInstance(7));

        assertThat(winLotto.rank(winLottoTest.lottoNumbers))
                .isEqualTo(LottoRank.FIRST);
    }

    @Test
    @DisplayName("로또 2등 당첨")
    void secondPlaceLottoPrize() {
        List<LottoNumber> input = List.of(1, 2, 3, 4, 5, 7).stream()
                .map(LottoNumber::getInstance)
                .collect(Collectors.toList());

        WinLotto winLotto = new WinLotto(new LottoNumbers(input), LottoNumber.getInstance(7));

        assertThat(winLotto.rank(winLottoTest.lottoNumbers))
                .isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("로또 3등 당첨")
    void thirdPlaceLottoPrize() {
        List<LottoNumber> input = List.of(1, 2, 3, 4, 5, 8).stream()
                .map(LottoNumber::getInstance)
                .collect(Collectors.toList());

        WinLotto winLotto = new WinLotto(new LottoNumbers(input), LottoNumber.getInstance(7));

        assertThat(winLotto.rank(winLottoTest.lottoNumbers))
                .isEqualTo(LottoRank.THIRD);
    }

    @Test
    @DisplayName("로또 4등 당첨")
    void fourthPlaceLottoPrize() {
        List<LottoNumber> input = List.of(1, 2, 3, 4, 9, 8).stream()
                .map(LottoNumber::getInstance)
                .collect(Collectors.toList());

        WinLotto winLotto = new WinLotto(new LottoNumbers(input), LottoNumber.getInstance(7));

        assertThat(winLotto.rank(winLottoTest.lottoNumbers))
                .isEqualTo(LottoRank.FOURTH);
    }

    @Test
    @DisplayName("로또 5등 당첨")
    void fifthPlaceLottoPrize() {
        List<LottoNumber> input = List.of(1, 2, 3, 8, 9, 10).stream()
                .map(LottoNumber::getInstance)
                .collect(Collectors.toList());

        WinLotto winLotto = new WinLotto(new LottoNumbers(input), LottoNumber.getInstance(7));

        assertThat(winLotto.rank(winLottoTest.lottoNumbers))
                .isEqualTo(LottoRank.FIFTH);
    }

    @Test
    @DisplayName("로또 미당첨")
    void notWinningLottoPrize() {
        List<LottoNumber> input = List.of(1, 2, 7, 8, 9, 10).stream()
                .map(LottoNumber::getInstance)
                .collect(Collectors.toList());

        WinLotto winLotto = new WinLotto(new LottoNumbers(input), LottoNumber.getInstance(7));

        assertThat(winLotto.rank(winLottoTest.lottoNumbers))
                .isEqualTo(LottoRank.NOTHING);
    }
}
