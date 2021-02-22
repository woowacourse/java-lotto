package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Seller {

    public static final String DELIMITER = ",";
    private static final LottoGenerator lottoGenerator = new LottoGenerator();

    public List<Lotto> sell(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i=0; i<count; i++) {
            lottos.add(new Lotto(lottoGenerator.generateLotto()));
        }
        return lottos;
    }

    public List<LottoNumber> sell(String input) {
        List<LottoNumber> lottoNumber = new ArrayList<>();
        for (String number : input.split(DELIMITER, -1)) {
            number = number.trim();
            lottoNumber.add(new LottoNumber(number));
        }
        return lottoNumber;
    }
}
