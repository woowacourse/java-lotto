package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumber {

    private static final int LOTTO_SIZE = 6;
    private List<Integer> lottoNumbers = new ArrayList<>();

    public List<Integer> createLottoNumbers() {
        getRangeLottoNumbers();
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(0, LOTTO_SIZE);
    }

    private void getRangeLottoNumbers() {
        lottoNumbers = LottoRange.getRangeLottoNumbers();
    }
}
