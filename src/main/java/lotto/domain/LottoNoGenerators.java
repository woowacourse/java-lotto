package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNoGenerators {
    private List<LottoNoGenerator> lottoNoGenerators;

    LottoNoGenerators() {
        lottoNoGenerators = new ArrayList<>();
    }

    int size() {
        return lottoNoGenerators.size();
    }

    LottoNoGenerator get(int index) {
        return lottoNoGenerators.get(index);
    }

    void add(LottoNoGenerator lottoNoGenerator) {
        lottoNoGenerators.add(lottoNoGenerator);
    }
}
