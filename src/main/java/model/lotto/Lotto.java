package model.lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import model.winningnumber.LottoWinningNumberDTO;
import model.result.Statistics;
import model.bonusball.BonusBallDTO;

public class Lotto {
    private static final int CHECKING_BONUS_NUMBER = 5;

    private final Set<Integer> numbers;

    public Lotto(Set<Integer> numbers) {
        this.numbers = numbers;
    }

    public void compare(BonusBallDTO bonusBallDTO, LottoWinningNumberDTO winningNumberDTO) {
        Set<Integer> winningNumbers = winningNumberDTO.getWinningNumbers();
        long count = compareWithWinningNumber(winningNumbers);

        if (count == CHECKING_BONUS_NUMBER) {
            compareWithBonusAndStore(bonusBallDTO);
            return;
        }
        storeResult(count);
    }

    private long compareWithWinningNumber(Set<Integer> winningNumbers) {
        return numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private void compareWithBonusAndStore(BonusBallDTO bonusBallDTO) {
        if (numbers.contains(bonusBallDTO.getNumber())) {
            Statistics.BONUS.addCount();
            return;
        }
        Statistics.FIVE.addCount();
    }

    private void storeResult(long count) {
        Arrays.stream(Statistics.values())
                .filter(statistics -> statistics.getMatchNumber() == count)
                .forEach(Statistics::addCount);
    }

    public LottoDTO getLottoDTO() {
        return new LottoDTO(numbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
