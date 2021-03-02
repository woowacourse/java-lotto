package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManualGenerator extends LottoGenerator<List<String>> {

    private static final String DELIMITER = ",";

    @Override
    public LottoGroup group(List<String> input) {
        List<Lotto> lottos = new ArrayList<>();
        for (String lottoNumbers : input) {
            lottos.add(generate(lottoNumbers));
        }
        return new LottoGroup(lottos);
    }

    @Override
    public Lotto generate() {
        return null;
    }

    public Lotto generate(String input) {
        Set<LottoNumber> lottoNumber = new HashSet<>();
        for (String value : input.split(DELIMITER, -1)) {
            value = value.trim();
            lottoNumber.add(LottoNumber.of(value));
        }
        return new Lotto(lottoNumber);
    }
}
