package service;

import domain.LottoDispenser;
import domain.WinningNumber;
import repository.LottoRepository;
import repository.WinningNumberRepository;

public class LottoService {
    private final LottoRepository lottoRepository;
    private final WinningNumberRepository winningNumberRepository;

    public LottoService(LottoRepository lottoRepository, WinningNumberRepository winningNumberRepository) {
        this.lottoRepository = lottoRepository;
        this.winningNumberRepository = winningNumberRepository;
    }

    public void inputBuyLottoMoney(String inputBuyLottoMoney) {
        lottoRepository.saveBuyLottoMoney(new LottoDispenser(inputBuyLottoMoney));
    }

    public void inputWinningNumber(String inputWinningNumber) {
        winningNumberRepository.saveWinningNumber(new WinningNumber(inputWinningNumber));
    }
}
