package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<LottoDTO> getLottoStorageDTO() {
        List<LottoDTO> lottoDTOS = new ArrayList<>();
        lottoStorage.forEach(lotto -> lottoDTOS.add(lotto.getLottoDTO()));
        return lottoDTOS;
    }
}
