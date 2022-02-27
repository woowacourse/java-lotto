package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private final Set<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        lottoNumbers = new HashSet<>();
        for (Integer number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
    }

    public Prize calculateRank(WinningNumber winningNumber) {
        int matched = matchedRegularNumbers(winningNumber);
        boolean hasBonus = hasMatchedNumber(winningNumber.getBonus());
        return Prize.getWinnerPrizeByMatched(matched, hasBonus);
    }

    private int matchedRegularNumbers(WinningNumber winningNumber) {
        return (int) winningNumber.getWinningNumbers().stream()
                .filter(lottoNumbers::contains).count();
    }

    private boolean hasMatchedNumber(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

}
