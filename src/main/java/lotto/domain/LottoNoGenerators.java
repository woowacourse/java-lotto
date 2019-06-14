package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNoGenerators {
    private List<LottoNoGenerator> lottoNoGenerators;

    public LottoNoGenerators() {
        lottoNoGenerators = new ArrayList<>();
    }

    public int size() {
        return lottoNoGenerators.size();
    }

    public LottoNoGenerator get(int index) {
        return lottoNoGenerators.get(index);
    }

    public void add(LottoNoGenerator lottoNoGenerator) {
        lottoNoGenerators.add(lottoNoGenerator);
    }
}
