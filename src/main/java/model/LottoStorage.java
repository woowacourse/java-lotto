package model;

import java.util.ArrayList;
import java.util.List;

public class LottoStorage {
    private List<Lotto> lottoStorage;

    public LottoStorage(LottoCount lottoCount) {
        this.lottoStorage = store(lottoCount);
    }

    private List<Lotto> store(LottoCount lottoCount) {
        List<Lotto> lottos = new ArrayList<>();

        while (!lottoCount.isZero()) {
            lottos.add(new Lotto());
            lottoCount.makeLotto();
        }
        return lottos;
    }
}
