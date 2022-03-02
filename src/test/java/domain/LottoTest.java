package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {

    @Test
    @DisplayName("올바른 크기의 로또 생성")
    void createLotto_makeRightLotto() {
        Set<Integer> numbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.getLottoNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("3개 일치시 5등이다.")
    void lotto_calculateRightFifthRank() {
        Lotto lotto = new Lotto(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)));

        String numbers = "1, 2, 3, 10, 11, 12";
        String bonus = "45";
        WinningNumbers winningLotto = new WinningNumbers(numbers, bonus);

        Rank winnerPrice = lotto.calculateRank(winningLotto);
        assertThat(winnerPrice).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("4개 일치시 4등이다.")
    void lotto_calculateRightFourthRank() {
        Lotto lotto = new Lotto(new HashSet<>(Arrays.asList(1, 2, 3, 4, 6, 8)));

        String numbers = "1, 2, 3, 4, 5, 12";
        String bonus = "6";
        WinningNumbers winningLotto = new WinningNumbers(numbers, bonus);

        Rank winnerPrice = lotto.calculateRank(winningLotto);
        assertThat(winnerPrice).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("5개 일치와 보너스가 있다면 2등이다.")
    void lotto_calculateRightSecondRank() {
        Lotto lotto = new Lotto(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)));

        String numbers = "1, 2, 3, 4, 5, 12";
        String bonus = "6";
        WinningNumbers winningLotto = new WinningNumbers(numbers, bonus);

        Rank winnerPrice = lotto.calculateRank(winningLotto);
        assertThat(winnerPrice).isEqualTo(Rank.SECOND);
    }

}
