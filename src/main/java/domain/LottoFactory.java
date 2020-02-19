package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoFactory {

    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int LOTTO_SIZE = 6;

    public static int createLottoNumber(int number) {
        if (isNotLottoNumber(number)) {
            throw new IllegalArgumentException("로또 숫자 범위를 넘어섰습니다.");
        }
        return number;
    }

    private static boolean isNotLottoNumber(int number) {
        return number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER;
    }

    public static List<Integer> createOneLotto() {
        Set<Integer> lotto = new HashSet<>();
        while (lotto.size() < LOTTO_SIZE) {
            int number = RandomNumberGenerator.generate(MAX_LOTTO_NUMBER, MIN_LOTTO_NUMBER);
            lotto.add(createLottoNumber(number));
        }
        return new ArrayList<Integer>(lotto);
    }
}
