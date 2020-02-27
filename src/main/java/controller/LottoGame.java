package controller;

import domain.*;

public class LottoGame {
    private Lottos lottos;
    private WinningNumber winningNumber;
    private LottoResult lottoResult;

    public LottoGame(LottoCount lottoCount){
        this.lottos = LottosGenerator.generateTotal(lottoCount);
//        this.winningNumber = inputWinningNumberWithValidation();
        this.lottoResult = new LottoResult();
    }


}
