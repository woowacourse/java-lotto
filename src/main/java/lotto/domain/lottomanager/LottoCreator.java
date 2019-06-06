package lotto.domain.lottomanager;

import lotto.domain.lottomanager.shufflerule.Shuffle;
import lotto.utils.NullCheckUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoCreator {
    private static final int SUBLIST_FIRST_INDEX = 0;

    private static List<LottoNumber> possibleNumbers;

    static {
        possibleNumbers = IntStream.rangeClosed(LottoConstant.LIMIT_MIN_NUM, LottoConstant.LIMIT_MAX_NUM)
                .mapToObj(LottoNumber::createLottoNumber)
                .collect(Collectors.toList());
    }

    public static LottoTicket createAutoTickets(Shuffle shuffle) {
        NullCheckUtil.checkNullShuffle(shuffle);

        shuffle.shuffleLottoNumbers(possibleNumbers);

        List<LottoNumber> lottoNumbers = new ArrayList<>(getPossibleNumbers());
        Collections.sort(lottoNumbers);

        return LottoTicket.createLottoTicket(lottoNumbers);
    }

    private static List<LottoNumber> getPossibleNumbers() {
        return possibleNumbers.subList(SUBLIST_FIRST_INDEX, LottoConstant.LOTTO_NUM_SIZE);
    }

    public static LottoTicket createManualTickets(List<Integer> lottoNumbers) {
        NullCheckUtil.checkNullIntegerNumbers(lottoNumbers);
        Collections.sort(lottoNumbers);

        List<LottoNumber> convertedLottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::createLottoNumber)
                .collect(Collectors.toList());

        return LottoTicket.createLottoTicket(convertedLottoNumbers);
    }
}
