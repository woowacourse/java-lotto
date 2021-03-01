package domain.number;

import domain.lotto.Lotto;
import domain.lotto.LottoBall;

import java.util.List;

public interface LottoNumberGenerator {
    int MIN_NUMBER = LottoBall.getMinNumber();
    int MAX_NUMBER = LottoBall.getMaxNumber();
    int LOTTO_BALLS_IN_LOTTO = Lotto.getLottoBallsNumber();

    List<List<Integer>> createLottoNumberBundle(final int quantity);

    List<Integer> createLottoNumber();
}
