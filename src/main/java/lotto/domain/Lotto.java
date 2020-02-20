package lotto.domain;

import java.util.List;

public class Lotto {
    private static final int PRICE = 1_000;
    private List<Integer> lottoNumbers;

    Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    static int convertMoneyToLottosSize(int money) {
        return money / PRICE;
    }

    int matchWinningNumbers(List<Integer> winningNumbers) {
        // 질문: List구현체가 ArrayList일 경우, parallel을 쓰면 속도가 빨라진다고 해서 써 봤는데요. 혹시 잘못 쓴 것은 아닌지, parallel을 언제 쓰면 좋은 지 궁금합니다.
        return (int)lottoNumbers.stream()
            .parallel()
            .filter(lottoNumber -> winningNumbers.contains(lottoNumber))
            .count();
    }

    public boolean matchBonusBall(int bonusBall) {
        return lottoNumbers.contains(bonusBall);
    }

    private boolean contains(int bonusBall) {
        return this.lottoNumbers.contains(bonusBall);
    }

    // public static boolean matchBonusBall(int bonusBall) {
    //     return this.contains(bonusBall);
    // }
}
