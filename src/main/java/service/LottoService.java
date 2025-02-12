package service;

import repository.LottoRepository;

public class LottoService {
    private final LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public void inputBuyLottoMoney(String inputBuyLottoMoney) {
        lottoRepository.saveBuyLottoMoney(inputBuyLottoMoney);
    }
}
