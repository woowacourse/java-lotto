package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public void issueLotto(Money money) {
        BigDecimal shareValue = money.divide(LOTTO_PRICE);
        issuedLottoNumbers = generate(shareValue.intValue());
    }

    public LottoResult summarize() {
        LottoResult result = new LottoResult();
        issuedLottoNumbers.stream()
                .map(winningLottoNumbers::getRankBy)
                .forEach(result::add);
        return result;
    }

    public List<LottoNumbers> generate(int quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(i -> lottoNumbersGenerator.createLottoNumbers())
                .collect(Collectors.toList());
    }
}
