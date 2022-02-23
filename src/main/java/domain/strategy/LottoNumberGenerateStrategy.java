package domain.strategy;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface LottoNumberGenerateStrategy {
    List<Integer> generateWinningNumbers();

    static List<Integer> getLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(i);
        }

        return lottoNumbers;
    }
}
