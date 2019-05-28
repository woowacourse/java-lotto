package lotto.domain.generator;

import lotto.domain.LottoNo;

import java.util.ArrayList;
import java.util.List;

public class LottoNosManualGenerator implements LottoNosGenerator {
    private static final String DELIMITER = ",";

    private final String input;

    public LottoNosManualGenerator(final String input) {
        this.input = input;
    }

    @Override
    public List<LottoNo> generate() {
        List<LottoNo> lottoNos = new ArrayList<>(6);
        for (final String in : input.split(DELIMITER)) {
            int number = toInt(in.trim());
            lottoNos.add(LottoNo.from(number));
        }
        return lottoNos;
    }

    private int toInt(String s) {
        return Integer.parseInt(s);
    }
}
