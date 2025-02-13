package repository;

import domain.LottoDispenser;

public class LottoRepository {
    private LottoDispenser lottoDispenser;
    public void saveBuyLottoMoney(LottoDispenser lottoDispenser) {
        this.lottoDispenser = lottoDispenser;
    }
}
