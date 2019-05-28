package lotto.domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumber {

    private static final int MAX_LOTTO_NUMBER = 46;
    private static final int MIN_LOTTO_NUMBER = 1;
    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        if (!this.isValidLottoNumber(lottoNumber)) {
            throw new InvalidLottoNumberException(lottoNumber + "은 로또 수 범위를 벗어났습니다.");
        }
        this.lottoNumber = lottoNumber;
    }

    private boolean isValidLottoNumber(int lottoNumber) {
//        List<Integer> lottoNumbers=new ArrayList<>();
//        for (int i = 1; i <=46 ; i++) {
//            lottoNumbers.add(i);
//        }
//        Collections.shuffle(lottoNumbers);
//        List<Integer> lotto=lottoNumbers.subList(0,6);
        return lottoNumber >= MIN_LOTTO_NUMBER && lottoNumber <= MAX_LOTTO_NUMBER;
    }
}
