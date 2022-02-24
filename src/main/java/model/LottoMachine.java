package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {
    private final static Money LOTTO_PRICE = new Money(1000);
    private WinningLottoNumbers winningLottoNumbers;
    private LottoNumbersGenerator lottoNumbersGenerator;
    private List<LottoNumbers> issuedLottoNumbers;

    public LottoMachine(LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbersGenerator = lottoNumbersGenerator;
    }

    public LottoMachine(WinningLottoNumbers winningLottoNumbers, LottoNumbersGenerator lottoNumbersGenerator) {
        this.winningLottoNumbers = winningLottoNumbers;
        this.lottoNumbersGenerator = lottoNumbersGenerator;
        this.issuedLottoNumbers = new ArrayList<>();
    }

    public List<LottoNumbers> issueLotto(Money money) {
        BigDecimal shareValue = money.divide(LOTTO_PRICE);
        issuedLottoNumbers = generate(shareValue.intValue());
        return issuedLottoNumbers;
    }

    public List<LottoNumbers> generate(int quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(i -> lottoNumbersGenerator.createLottoNumbers())
                .collect(Collectors.toList());
    }

    public List<LottoNumbers> getIssuedLottoNumbers() {
        return issuedLottoNumbers;
    }
}
