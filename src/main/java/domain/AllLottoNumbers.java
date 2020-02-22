package domain;

import java.util.ArrayList;
import java.util.List;

public class AllLottoNumbers {
    private static final List<LottoNumber> allLottoNumbers = new ArrayList<>();
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;

    static {
        for (int index = MIN_LOTTO_NUMBER; index <= MAX_LOTTO_NUMBER; index ++){
            allLottoNumbers.add(new LottoNumber(index));
        }
    }

    public static List<LottoNumber> getAllLottoNumbers(){
        return allLottoNumbers;
    }
}
