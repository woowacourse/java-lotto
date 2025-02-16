package util;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;
import model.Lotto;
import model.LottoNumber;

public class LottoUtil {

    public static Lotto generateTestLotto(int... values) {
        return new Lotto(
                Arrays.stream(values)
                        .mapToObj(LottoNumber::new)
                        .collect(Collectors.toCollection(TreeSet::new))
        );
    }
}
