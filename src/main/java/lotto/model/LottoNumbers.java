package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoNumbers {
    private final static List<Integer> lottoNumbers = new ArrayList<>();

    public LottoNumbers () {
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(i);
        }
    }

    public List<Integer> getLottoNumbers(){
        return lottoNumbers;
    }
}
