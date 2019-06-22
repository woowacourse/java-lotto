package service;

import model.LottoResult;
import model.Lottos;
import model.WinningNumbers;

public class LottoVO {
    private final int round;
    private final WinningNumbers winningNumbers;
    private final Lottos lottos;
    private final LottoResult result;

    public LottoVO(int round, WinningNumbers winningNumbers, Lottos lottos, LottoResult result) {
        this.round = round;
        this.winningNumbers = winningNumbers;
        this.lottos = lottos;
        this.result = result;
    }

    public int round() {
        return this.round;
    }

    public WinningNumbers winningNumbers() {
        return this.winningNumbers;
    }

    public Lottos lottos() {
        return this.lottos;
    }

    public LottoResult result() {
        return this.result;
    }
}