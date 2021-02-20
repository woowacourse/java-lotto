package lottogame.domain.lotto;

import java.util.List;

public class Lotto {
    private final LottoNumber lottoNumber;

    public Lotto(LottoNumber lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public List<Integer> values() {
        return lottoNumber.values();
    }

//    public int match(Lotto winningLotto) {
//        return (int) numbers.stream()
//                .filter(number -> winningLotto.contains(number))
//                .count();
//    }
//
//    public boolean contains(int number) {
//        return numbers.contains(number);
//    }
//
//    public boolean containsBonus(WinningLotto winningLotto) {
//        return numbers.contains(winningLotto.getBonusBall());
//    }
}
