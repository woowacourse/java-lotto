package lotto.domain.lottomanager;

import lotto.domain.lottomanager.shufflerule.Shuffle;
import lotto.utils.NullCheckUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoCreator {
    private static List<LottoNumber> possibleNumbers;

    static {
        possibleNumbers = IntStream.rangeClosed(LottoConstant.LIMIT_MIN_NUM, LottoConstant.LIMIT_MAX_NUM)
                .mapToObj(LottoNumber::createLottoNumber)
                .collect(Collectors.toList());
    }

    public static LottoTicket getLottoTicket(Shuffle shuffle) {
        NullCheckUtil.checkNullShuffle(shuffle);

        shuffle.shuffleLottoNumbers(possibleNumbers);

        List<LottoNumber> lottoNumbers = new ArrayList<>(getPossibleNumbers());
        Collections.sort(lottoNumbers);

        return LottoTicket.createLottoTicket(lottoNumbers);
    }

    private static List<LottoNumber> getPossibleNumbers() {
        return possibleNumbers.subList(LottoConstant.SUBLIST_FIRST_INDEX, LottoConstant.LOTTO_NUM_SIZE);
    }
}
