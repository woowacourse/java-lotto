package repository;

import domain.LottoDispenser;

public class LottoRepository {
    private LottoDispenser lottoDispenser;
    public void saveLottoDispenser(LottoDispenser lottoDispenser) {
        this.lottoDispenser = lottoDispenser;
    }

    public LottoDispenser getLottoDispenser() {
        return lottoDispenser;
    }
}
