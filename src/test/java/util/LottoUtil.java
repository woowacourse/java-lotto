package util;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import model.Lotto;
import model.LottoNumber;

public class LottoUtil {

    public static Lotto generateTestLotto(List<Integer> values) {
        return new Lotto(
                values.stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toCollection(TreeSet::new))
        );
    }
}
