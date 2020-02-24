package lotto.generator;

import lotto.domain.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;

public class LottoNumberSelectedGenerator implements LottoNumberGenerator {
    private static final String SPLIT_DELIMETER = ",";
    private Set<LottoNumber> lottoNumbers;

    public LottoNumberSelectedGenerator(String lottoNumbersInput) {
        String[] splitedLottoNumbers = getSplitedLottoNumbers(lottoNumbersInput);
        lottoNumbers = createLottoNumbers(splitedLottoNumbers);
    }

    private String[] getSplitedLottoNumbers(String lottoNumbers) {
        return lottoNumbers.split(SPLIT_DELIMETER);
    }

    private Set<LottoNumber> createLottoNumbers(String[] splitedLottoNumbers) {
        return Arrays.stream(splitedLottoNumbers)
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public Set<LottoNumber> create() {
        return lottoNumbers;
    }
}
