package lotto.domain;

import java.util.List;

public class Lotto {
    private final int PRICE = 1_000;
    private List<Integer> lottoNumbers;

    Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    int convertMoneyToLottosSize(int money) {
        return money / PRICE;
    }

    int match(List<Integer> winningNumbers, int bonusBall) {
        // 질문: List구현체가 ArrayList일 경우, parallel을 쓰면 속도가 빨라진다고 해서 써 봤는데요. 혹시 잘못 쓴 것은 아닌지, parallel을 언제 쓰면 좋은 지 궁금합니다.
        return (int)lottoNumbers.stream()
            .parallel()
            .filter(lottoNumber -> winningNumbers.contains(lottoNumber))
            .count();
    }
}
