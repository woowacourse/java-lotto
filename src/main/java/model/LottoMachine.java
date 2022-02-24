package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private final static Money LOTTO_PRICE = new Money(1000);
    private final WinningLottoNumbers winningLottoNumbers;
    private final LottoNumbersGenerator lottoNumbersGenerator;
    private List<LottoNumbers> issuedLottoNumbers;

    public LottoMachine(WinningLottoNumbers winningLottoNumbers, LottoNumbersGenerator lottoNumbersGenerator) {
        this.winningLottoNumbers = winningLottoNumbers;
        this.lottoNumbersGenerator = lottoNumbersGenerator;
        this.issuedLottoNumbers = new ArrayList<>();
    }

    public LottoResult summarize(List<LottoNumbers> lottoNumbersList) {
        LottoResult result = new LottoResult();
        lottoNumbersList.stream()
            .map(winningLottoNumbers::getRankBy)
            .forEach(result::add);
        return result;
    }

    public void issueLotto(Money money) {
        BigDecimal shareValue = money.divide(LOTTO_PRICE);
        issuedLottoNumbers = lottoNumbersGenerator.generate(shareValue.intValue());
    }

    public LottoResult summarize() {
        return summarize(issuedLottoNumbers);
    }
}
