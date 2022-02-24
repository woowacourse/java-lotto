package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        lottoNumbers = new ArrayList<>();
        numbers.forEach(number -> lottoNumbers.add(new LottoNumber(number)));
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public Prize calculateRank(WinningNumber winningNumber) {
        int matched = matchedRegularNumbers(winningNumber);
        boolean hasBonus = matchedBonusNumber(winningNumber.getBonus());
        return Prize.getWinnerPrizeByMatched(matched, hasBonus);
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

    private boolean checkSecond(int matched, boolean hasBonus) { // checkSecond는 두번쨰 체크?느낌이 나서 checkSecondPrize가 좋지 않을까?
        return (matched == Prize.THIRD.getMatched()) && hasBonus;
    }

}
