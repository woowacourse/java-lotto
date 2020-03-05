package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoFactory {
    public static Lotto create(List<Integer> values) {
        return new Lotto(values.stream().map(LottoNumber::create).collect(
            Collectors.toSet()));
    }
}
