package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;

public class InputConvertor {

    private InputConvertor() {
    }

    public static List<Lotto> toLottoList(List<List<Integer>> lottos) {
        return lottos.stream()
                .map(Lotto::from)
                .collect(Collectors.toList());
    }
}
