package model.lotto;

import model.winningnumber.LottoWinningNumberDTO;
import model.result.Statistics;
import model.bonusball.BonusBallDTO;

import java.util.Arrays;
import java.util.List;

public class Lotto {
    private static final int CHECKING_BONUS_NUMBER = 5;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public LottoDTO getLottoDTO() {
        return new LottoDTO(numbers);
    }

    public void compare(BonusBallDTO bonusBallDTO, LottoWinningNumberDTO winningNumberDTO) {
        List<Integer> winningNumbers = winningNumberDTO.getWinningNumbers();
        long count = compareWithWinningNumber(winningNumbers);

        if (count == CHECKING_BONUS_NUMBER) {
            compareWithBonusAndStore(bonusBallDTO);
            return;
        }
        storeResult(count);
    }

    private long compareWithWinningNumber(List<Integer> winningNumbers) {
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
}
