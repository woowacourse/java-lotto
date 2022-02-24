package model;

import java.util.List;

public class LottoMachine {
    private final WinningLottoNumbers winningLottoNumbers;
    private final LottoNumbersGenerator lottoNumbersGenerator;

    public LottoMachine(WinningLottoNumbers winningLottoNumbers, LottoNumbersGenerator lottoNumbersGenerator) {
        this.winningLottoNumbers = winningLottoNumbers;
        this.lottoNumbersGenerator = lottoNumbersGenerator;
    }

    public LottoResult summarize(List<LottoNumbers> lottoNumbersList) {
        LottoResult result = new LottoResult();
        lottoNumbersList.stream()
            .map(winningLottoNumbers::getRankBy)
            .forEach(result::add);
        return result;
    }
}
