package domain;

import java.util.List;

public class WinningLotto {
    private Lotto winningLotto;
    private LottoNumber bonusBall;

    public WinningLotto(List<LottoNumber> numbers, int bonusBallNumber){
        winningLotto = new Lotto(numbers);
        bonusBall = new LottoNumber(bonusBallNumber);
    }
}
