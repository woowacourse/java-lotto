package lotto.generator;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.view.OutputView;

import java.util.*;
import java.util.stream.Collectors;

public class LottoSelectedGenerator implements LottoGenerator {
    private Set<LottoNumber> lottoNumbers;

    public LottoSelectedGenerator(String lottoNumbersInput) {
        String[] splitedLottoNumbers = getSplitedLottoNumbers(lottoNumbersInput);
        lottoNumbers = createLottoNumbers(splitedLottoNumbers);
    }

    private String[] getSplitedLottoNumbers(String lottoNumbers) {
        return lottoNumbers.split(OutputView.SPLIT_DELIMETER);
    }

    private Set<LottoNumber> createLottoNumbers(String[] splitedLottoNumbers) {
        return Arrays.stream(splitedLottoNumbers)
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public Lotto create() {
        return new Lotto(lottoNumbers);
    }
}
