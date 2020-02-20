package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_LENGTH = 6;

    private static List<Integer> lottoNumbers = new ArrayList<>();

    public LottoFactory() {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            lottoNumbers.add(i);
        }
    }

    public static List<Integer> createLottoNumbers() {
        List<Integer> lotto = new ArrayList<>();
        Collections.shuffle(lottoNumbers);
        for (int i = 0; i < LOTTO_LENGTH; i++) {
            lotto.add(lottoNumbers.get(i));
        }
        return lotto;
    }
}
