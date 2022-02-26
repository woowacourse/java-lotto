package lotto.domain.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.vo.LottoNumber;

public class AutoLottoTicketGenerator implements LottoTicketGenerator {

    private static final int LOTTO_NUMBERS_FROM_INDEX = 0;
    private static final int LOTTO_NUMBERS_TO_INDEX = 6;

    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> lottoTotalNumbers = LottoNumber.getLottoTotalNumbers();
        Collections.shuffle(lottoTotalNumbers);

        List<LottoNumber> lottoNumbers = lottoTotalNumbers.subList(LOTTO_NUMBERS_FROM_INDEX, LOTTO_NUMBERS_TO_INDEX);
        Collections.sort(lottoNumbers);

        return new ArrayList<>(lottoNumbers);
    }
}
