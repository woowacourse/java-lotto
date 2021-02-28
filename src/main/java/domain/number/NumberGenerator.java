package domain.number;

import java.util.List;

public interface NumberGenerator {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    public static final int LOTTO_BALLS_IN_LOTTO = 6;

    public List<Integer> createLottoNumber();
}
