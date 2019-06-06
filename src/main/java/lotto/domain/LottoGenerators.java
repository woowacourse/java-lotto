package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerators {
    private List<LottoGenerator> lottoGenerators;

    LottoGenerators() {
        lottoGenerators = new ArrayList<>();
    }

    int size() {
        return lottoGenerators.size();
    }

    LottoGenerator get(int index) {
        return lottoGenerators.get(index);
    }

    void add(LottoGenerator lottoGenerator) {
        lottoGenerators.add(lottoGenerator);
    }
}
