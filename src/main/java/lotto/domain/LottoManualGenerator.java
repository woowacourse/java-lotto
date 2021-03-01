package lotto.domain;

import lotto.util.LottoGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoManualGenerator implements LottoGenerator {
    @Override
    public Lotto generate(String... varargs) {

        String[] numbersArray = varargs[0].split(", ");
        List<LottoNumber> lotto = new ArrayList<>();

        for (String number : numbersArray) {
            lotto.add(new LottoNumber(Integer.parseInt(number)));
        }

        return new Lotto(lotto);
    }
}
