package lotto.domain;

import lotto.domain.ShuffleRule.Shuffle;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {
    private static List<LottoNumber> lottoNumbers;

    static {
        lottoNumbers = IntStream.rangeClosed(LottoConstant.LIMIT_MINIMUM_NUMBER, LottoConstant.LIMIT_MAXIMUM_NUMBER)
                .mapToObj(LottoNumber::createLottoNumber)
                .collect(Collectors.toList());
    }

    public static LottoTicket getLottoTicket(Shuffle shuffle) {
        return LottoTicket.createLottoTicket(getLottoNumbers(shuffle));
    }

    private static List<LottoNumber> getLottoNumbers(Shuffle shuffle) {
        return shuffle.getShuffledLottoNumbers(lottoNumbers);
    }
}
