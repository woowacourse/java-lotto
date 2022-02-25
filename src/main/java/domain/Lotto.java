package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private Set<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        lottoNumbers = new HashSet<>();
        for (Integer number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public Prize calculateRank(WinningNumber winningNumber) {
        int matched = matchedRegularNumbers(winningNumber);
        boolean hasBonus = hasMatchedNumber(winningNumber.getBonus());
        return Prize.getWinnerPrizeByMatched(matched, hasBonus);
    }

    private int matchedRegularNumbers(WinningNumber winningNumber) {
        return winningNumber.getWinningNumbers().stream()
                .filter(lottoNumber -> lottoNumbers.contains(lottoNumber))
                .collect(Collectors.toList())
                .size();
    }

    private boolean hasMatchedNumber(LottoNumber bonus) {
        return lottoNumbers.contains(bonus);
    }

}
