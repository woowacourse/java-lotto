package util;

import java.util.Set;
import java.util.TreeSet;
import model.Lotto;
import model.LottoNumber;

public class LottoUtil {

    public static Lotto generateTestLotto(int... values) {
        Set<LottoNumber> numbers = new TreeSet<>();
        for (int value : values) {
            numbers.add(new LottoNumber(value));
        }
        return new Lotto(numbers);
    }
}
