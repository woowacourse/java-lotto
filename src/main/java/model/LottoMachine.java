package model;

import java.util.List;

public class LottoMachine {
    private final WinningLottoNumbers winningLottoNumbers;

    public LottoMachine(WinningLottoNumbers winningLottoNumbers) {
        this.winningLottoNumbers = winningLottoNumbers;
    }

    public LottoResult summarize(List<LottoNumbers> lottoNumbersList) {
        LottoResult result = new LottoResult();

        for (LottoNumbers lottoNumbers : lottoNumbersList) {
            LottoRank rank = winningLottoNumbers.judge(lottoNumbers);
            result.add(rank);
        }

        return result;
    }
}
