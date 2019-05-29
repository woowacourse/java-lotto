package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    public static Lotto createLottoAutomatically() {
        return new Lotto(LottoNumber.getShuffledNumber());
    }

    public static Lotto createLottoManually(List<Integer> userNumbers) {
        List<LottoNumber> numbers = new ArrayList<>();
        for (Integer userNumber : userNumbers) {
            numbers.add(LottoNumber.get(userNumber));
        }
        return new Lotto(numbers);
    }
}
