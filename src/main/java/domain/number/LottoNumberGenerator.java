package domain.number;

import java.util.List;

public interface LottoNumberGenerator {
    int MIN_NUMBER = 1;
    int MAX_NUMBER = 45;
    int LOTTO_BALLS_IN_LOTTO = 6;

    List<List<Integer>> createLottoNumberBundle(final int quantity);

    List<Integer> createLottoNumber();
}
