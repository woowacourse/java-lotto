package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class LottoManualGenerator implements LottoGenerator {
    private Stack<LottoNo> lottoNos;

    LottoManualGenerator(List<Integer> lottoNoList) {
        lottoNos = lottoNoList.stream()
                .map(LottoNo::of)
                .collect(Stack::new, Stack::add, Stack::addAll)
        ;
    }

    @Override
    public LottoNo generate() {
        return lottoNos.pop();
    }

    @Override
    public int size() {
        return lottoNos.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoManualGenerator)) return false;
        LottoManualGenerator that = (LottoManualGenerator) o;
        return Objects.equals(lottoNos, that.lottoNos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNos);
    }
}
