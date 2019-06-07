package lotto.domain;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LottoFactory {
    public static Lotto createLottoAutomatically() {
        return new Lotto(LottoNumber.getShuffledNumbers());
    }

    public static Lotto createLottoManually(List<String> manualNumbers) {
        Set<LottoNumber> numbers = new TreeSet<>();
        for (String manualNumber : manualNumbers) {
            numbers.add(LottoNumber.get(Integer.parseInt(manualNumber)));
        }
        return new Lotto(numbers);
    }
}