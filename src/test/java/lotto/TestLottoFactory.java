package lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoNumber;

public class TestLottoFactory {

    public static List<LottoNumber> getNumbers(int... num) {
        List<LottoNumber> numbers = new ArrayList<>();
        for (int i : num) {
            numbers.add(new LottoNumber(i));
        }
        return numbers;
    }
}
