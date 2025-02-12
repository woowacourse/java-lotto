package repository;

import domain.LottoDispenser;

public class LottoRepository {
    private LottoDispenser lottoDispenser;
    public void saveBuyLottoMoney(String inputBuyLottoMoney) {
        lottoDispenser = new LottoDispenser(inputBuyLottoMoney);
    }
}
