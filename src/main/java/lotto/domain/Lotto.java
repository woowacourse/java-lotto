package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    public static final int LOTTO_NUMBER = 6;
    private final List<Integer> numbers;

    public Lotto() {
        numbers = new ArrayList<>(LottoGenerator.generateNumbers());
    }

    public Lotto(List<Integer> selectedNumber) {
        numbers = new ArrayList<>(selectedNumber);
    }

    public LottoRank getLottoRank(List<Integer> winningNumbers, int bonusNumber) {
        List<Integer> integratedWinningNumbers = new ArrayList<>(winningNumbers);
        integratedWinningNumbers.add(bonusNumber);

        int count = (int)numbers.stream()
            .filter(integratedWinningNumbers::contains)
            .count();

        return LottoRank.getRank(count);
    }
}