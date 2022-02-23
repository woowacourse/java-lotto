package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private static final int FAILING_CRITERIA = 3;

    private List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        lottoNumbers = new ArrayList<>();
        numbers.forEach(number -> lottoNumbers.add(new LottoNumber(number)));
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public WinnerPrice calculateRank(WinningNumber winningNumber) {
        int matched = matchedRegularNumbers(winningNumber);
        boolean hasBonus = matchedBonusNumber(winningNumber.getBonus());
        if (checkSecond(matched, hasBonus)) {
            return WinnerPrice.SECOND;
        }
        if (matched < FAILING_CRITERIA) {
            return WinnerPrice.FAIL;
        }
        return WinnerPrice.getWinnerPriceByMatched(matched);
    }

    private int matchedRegularNumbers(WinningNumber winningNumber) {
        return winningNumber.getWinningNumbers().stream()
                .filter(lottoNumber -> lottoNumbers.contains(lottoNumber))
                .collect(Collectors.toList())
                .size();
    }

    private boolean matchedBonusNumber(LottoNumber bonus) {
        return lottoNumbers.contains(bonus);
    }

    private boolean checkSecond(int matched, boolean hasBonus) {
        return (matched == WinnerPrice.THIRD.getMatched()) && hasBonus;
    }

}
