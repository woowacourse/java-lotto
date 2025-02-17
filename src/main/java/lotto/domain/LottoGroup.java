package lotto.domain;

import java.util.List;

public class LottoGroup {
    private final List<Lotto> lottos;

    public LottoGroup(List<Lotto> item) {
        this.lottos = item;
    }

    public static LottoGroup from(List<Lotto> lottoList) {
        return new LottoGroup(lottoList);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public List<List<Integer>> toIntegerLottosList() {
        return lottos.stream().map(Lotto::toIntegerList).toList();
    }

    @Override
    public String toString() {
        return "LottoGroup{" +
                "lottos=" + lottos +
                '}';
    }
}
