package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Seller {

    public static final String DELIMITER = ",";
    private static final LottoGenerator lottoGenerator = new LottoGenerator();

    public List<Lotto> sell(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(lottoGenerator.generateLotto()));
        }
        return lottos;
    }

    public Set<LottoNumber> sell(String input) {
        Set<LottoNumber> lottoNumber = new HashSet<>();
        for (String number : input.split(DELIMITER, -1)) {
            number = number.trim();
            lottoNumber.add(LottoNumber.of(number));
        }
        return lottoNumber;
    }
}
