package lotto.domain.lottofactory;

import lotto.domain.shufflerule.Shuffle;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoCreator {
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
