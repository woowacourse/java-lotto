package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {

    @Test
    @DisplayName("3개 일치시 5등이다.")
    void lotto_calculateRightFifthRank() {
        Integer[] numbersArray = {1, 2, 3, 4, 5, 6};
        List<Integer> numbers = Arrays.asList(numbersArray);
        Lotto lotto = new Lotto(numbers);

        Integer[] winningNumbersArray = {1, 2, 3, 10, 11, 12};
        List<Integer> winningNumbers = Arrays.asList(winningNumbersArray);
        Lotto winningLotto = new Lotto(winningNumbers);

        LottoNumber bonus = new LottoNumber(45);

        WinnerPrice winnerPrice = lotto.calculateRank(winningLotto, bonus);
        assertThat(winnerPrice).isEqualTo(WinnerPrice.FIFTH);
    }

    @Test
    @DisplayName("5개 일치와 보너스가 있다면 5등이다.")
    void lotto_calculateRightSecondRank() {
        Integer[] numbersArray = {1, 2, 3, 4, 5, 6};
        List<Integer> numbers = Arrays.asList(numbersArray);
        Lotto lotto = new Lotto(numbers);

        Integer[] winningNumbersArray = {1, 2, 3, 4, 5, 12};
        List<Integer> winningNumbers = Arrays.asList(winningNumbersArray);
        Lotto winningLotto = new Lotto(winningNumbers);

        LottoNumber bonus = new LottoNumber(6);

        WinnerPrice winnerPrice = lotto.calculateRank(winningLotto, bonus);
        assertThat(winnerPrice).isEqualTo(WinnerPrice.SECOND);
    }

}
