package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private List<LottoBall> lottoBalls;

    public Lotto(List<Integer> numbers) {
        lottoBalls = new ArrayList<>();
        numbers.forEach(number -> lottoBalls.add(new LottoBall(number)));
    }

    public List<LottoBall> getLottoNumbers() {
        return lottoBalls;
    }

    public Prize calculateRank(WinningLotto winningLotto) {
        int matched = matchedRegularNumbers(winningLotto);
        boolean hasBonus = matchedBonusNumber(winningLotto.getBonus());
        return Prize.getWinnerPrizeByMatched(matched, hasBonus);
    }

    private int matchedRegularNumbers(WinningLotto winningLotto) {
        return winningLotto.getWinningNumbers().stream()
                .filter(lottoBall -> lottoBalls.contains(lottoBall))
                .collect(Collectors.toList())
                .size();
    }

    private boolean matchedBonusNumber(LottoBall bonus) {
        return lottoBalls.contains(bonus);
    }

}
