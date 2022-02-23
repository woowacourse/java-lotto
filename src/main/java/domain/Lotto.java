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

    public WinnerPrice calculateRank(Lotto winningLotto, LottoNumber bonus) {
        int matched = matchedRegularNumbers(winningLotto);
        boolean hasBonus = matchedBonusNumber(bonus);

        if (checkSecond(matched, hasBonus)) {
            return WinnerPrice.SECOND;
        }
        return WinnerPrice.getWinnerPriceByMatched(matched);
    }

    private int matchedRegularNumbers(Lotto winningLotto) {
        return winningLotto.getLottoNumbers().stream()
                .filter(lottoNumber -> lottoNumbers.contains(lottoNumber))
                .collect(Collectors.toList())
                .size();
    }

    private boolean matchedBonusNumber(LottoNumber bonus) {
        return lottoNumbers.contains(bonus);
    }

    private boolean checkSecond(int matched, boolean hasBonus) {
        return matched == 5 && hasBonus;
    }



}
