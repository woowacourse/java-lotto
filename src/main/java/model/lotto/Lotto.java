package model.lotto;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import model.result.WinningResult;
import model.winningnumber.LottoWinningNumberDTO;
import model.result.Rank;
import model.bonusball.BonusBallDTO;

public class Lotto {
    private static final int CHECKING_BONUS_NUMBER = 5;

    private final Set<Integer> numbers;

    public Lotto(Set<Integer> numbers) {
        this.numbers = numbers;
    }

    public void calcWinningNumber(WinningResult winningResult, BonusBallDTO bonusDTO, LottoWinningNumberDTO winningDTO) {
        Set<Integer> winningNumbers = winningDTO.getWinningNumbers();
        long count = countWinningNumber(winningNumbers);

        if (count == CHECKING_BONUS_NUMBER) {
            compareWithBonusAndStore(winningResult, bonusDTO);
            return;
        }
        storeResult(winningResult, count);
    }

    private long countWinningNumber(Set<Integer> winningNumbers) {
        return numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private void compareWithBonusAndStore(WinningResult winningResult, BonusBallDTO bonusBallDTO) {
        if (numbers.contains(bonusBallDTO.getNumber())) {
            winningResult.addCount(Rank.BONUS);
            return;
        }
        winningResult.addCount(Rank.FIVE);
    }

    private void storeResult(WinningResult winningResult, long count) {
        Arrays.stream(Rank.values())
                .filter(rank -> rank.getMatchNumber() == count)
                .forEach(winningResult::addCount);
    }

    public Set<Integer> getNumbers() {
        return Collections.unmodifiableSet(numbers);
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
