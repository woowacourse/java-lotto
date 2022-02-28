package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {

    @Test
    @DisplayName("올바른 크기의 로또 생성")
    void createLotto_makeRightLotto() {
        Integer[] numbersArray = {1, 2, 3, 4, 5, 6};
        List<Integer> numbers = Arrays.asList(numbersArray);
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.getLottoNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("3개 일치시 5등이다.")
    void lotto_calculateRightFifthRank() {
        Integer[] numbersArray = {1, 2, 3, 4, 5, 6};
        List<Integer> numbers = Arrays.asList(numbersArray);
        Lotto lotto = new Lotto(numbers);

        Integer[] winningNumbersArray = {1, 2, 3, 10, 11, 12};
        List<Integer> winningNumbers = Arrays.asList(winningNumbersArray);
        WinningNumbers winningLotto = new WinningNumbers(winningNumbers);
        winningLotto.addBonusNumber(45);

        Rank winnerPrice = lotto.calculateRank(winningLotto);
        assertThat(winnerPrice).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("4개 일치시 4등이다.")
    void lotto_calculateRightFourthRank() {
        Integer[] numbersArray = {1, 2, 3, 4, 6, 8};
        List<Integer> numbers = Arrays.asList(numbersArray);
        Lotto lotto = new Lotto(numbers);

        Integer[] winningNumbersArray = {1, 2, 3, 4, 5, 12};
        List<Integer> winningNumbers = Arrays.asList(winningNumbersArray);
        WinningNumbers winningLotto = new WinningNumbers(winningNumbers);
        winningLotto.addBonusNumber(6);

        Rank winnerPrice = lotto.calculateRank(winningLotto);
        assertThat(winnerPrice).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("5개 일치와 보너스가 있다면 2등이다.")
    void lotto_calculateRightSecondRank() {
        Integer[] numbersArray = {1, 2, 3, 4, 5, 6};
        List<Integer> numbers = Arrays.asList(numbersArray);
        Lotto lotto = new Lotto(numbers);

        Integer[] winningNumbersArray = {1, 2, 3, 4, 5, 12};
        List<Integer> winningNumbers = Arrays.asList(winningNumbersArray);
        WinningNumbers winningLotto = new WinningNumbers(winningNumbers);
        winningLotto.addBonusNumber(6);

        Rank winnerPrice = lotto.calculateRank(winningLotto);
        assertThat(winnerPrice).isEqualTo(Rank.SECOND);
    }

}
