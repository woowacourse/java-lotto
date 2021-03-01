package lotto.domain;

import lotto.util.LottoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LottoAutoGenerator implements LottoGenerator {

    private final List<LottoNumber> lottoNumbers;

    public LottoAutoGenerator() {
        this.lottoNumbers = new ArrayList<>();
        for (int i = LOTTO_MINIMUM_NUMBER; i < LOTTO_MAXIMUM_NUMBER; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    @Override
    public Lotto generate(String... varargs) {
        Collections.shuffle(lottoNumbers);
        return new Lotto(
                lottoNumbers.stream()
                        .limit(6)
                        .sorted(Comparator.comparing(LottoNumber::getLottoNumber))
                        .collect(Collectors.toList())
        );

    }
}
