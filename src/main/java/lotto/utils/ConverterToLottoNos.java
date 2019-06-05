package lotto.utils;

import lotto.domain.LottoNo;
import lotto.domain.generator.LottoNosManualGenerator;

import java.util.List;

public class ConverterToLottoNos {
    private ConverterToLottoNos() {
    }

    public static List<LottoNo> convert(final String numbers) {
        List<LottoNo> lottoNos = new LottoNosManualGenerator(numbers.substring(1, numbers.length() - 1)).generate();
        return lottoNos;
    }
}
