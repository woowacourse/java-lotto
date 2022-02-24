package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lotto.utils.Validation;

public class LottoWinningNumbers {

    public static final String LOTTO_STRING_DELIMITER = ",";
    private final Lotto winningLottoNumbers;
    private int bonusNumber;
    private HashMap<Rank, Integer> winningResult;

    public LottoWinningNumbers(String numbers, int bonusNumber) {
        initWinningResult();
        winningLottoNumbers = new Lotto(createWinningLottoNumbers(numbers));
        if (winningLottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
        this.bonusNumber = bonusNumber;
    }

    private List<Integer> createWinningLottoNumbers(String numbers) {
        return Arrays.stream(numbers.split(LOTTO_STRING_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public Lotto getWinningLottoNumbers() {
        return winningLottoNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public void calculateWinning(Lotto lotto) {
        int matchCount = (int) winningLottoNumbers.getNumbers()
                .stream()
                .filter(number -> lotto.getNumbers().contains(number))
                .count();
        boolean hasBonusNumber = lotto.getNumbers().contains(bonusNumber);
        Rank rank = Rank.matchRank(matchCount, hasBonusNumber);
        winningResult.put(rank, winningResult.get(rank)+1);
    }

    public void initWinningResult() {
        winningResult = new HashMap<>();
        for (Rank rank : Rank.values()) {
            winningResult.put(rank, 0);
        }
    }

    public int calculateWinningMoney() {
        int sum = 0;
        for (Rank rank : Rank.values()) {
            sum += winningResult.get(rank) * rank.getMoney();
        }
        return sum;
    }

    public int getRankCount(Rank rank){
        return winningResult.get(rank);
    }
}
