package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
    private static List<Integer> lottoNumbers = new ArrayList<>();

    public LottoFactory() {
        for (int i = 1; i < 46; i++) {
            lottoNumbers.add(i);
        }
    }

    public static List<Integer> createLottoNumbers() {
        Collections.shuffle(lottoNumbers);

        List<Integer> lotto = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            lotto.add(lottoNumbers.get(i));
            System.out.println(lotto.get(i));
        }

        return lotto;
    }
}
