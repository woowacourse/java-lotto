package lotto.domain;

import java.util.Set;
import java.util.TreeSet;

public class LottoFactory {
    public static Lotto createLottoAutomatically() {
        return new Lotto(LottoNumber.getShuffledNumber());
    }

    public static Lotto createLottoManually(String manualNumbers) {
        Set<LottoNumber> numbers = new TreeSet<>();
        for (String manualNumber : manualNumbers.split(",")) {
            numbers.add(LottoNumber.get(Integer.parseInt(manualNumber)));
        }
        return new Lotto(numbers);
    }
}