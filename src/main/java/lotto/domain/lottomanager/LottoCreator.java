package lotto.domain.lottomanager;

import lotto.domain.lottomanager.shufflerule.Shuffle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoCreator {
    private static final int SUBLIST_FIRST_INDEX = 0;
    private static final int LIMIT_MIN_NUM = 1;
    private static final int LIMIT_MAX_NUM = 45;
    private static final int LOTTO_NUM_SIZE = 6;
    private static final String ERROR_NULL_LOTTO_NUMBERS = "createManualTickets() has Null";
    private static final String ERROR_NULL_SHUFFLE = "createAutoTickets() has Null";

    private static List<LottoNumber> possibleNumbers;

    static {
        possibleNumbers = IntStream.rangeClosed(LIMIT_MIN_NUM, LIMIT_MAX_NUM)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public static LottoTicket createAutoTickets(Shuffle shuffle) {
        if (shuffle == null) {
            throw new IllegalArgumentException(ERROR_NULL_SHUFFLE);
        }

        shuffle.shuffleLottoNumbers(possibleNumbers);

        List<LottoNumber> lottoNumbers = new ArrayList<>(getPossibleNumbers());
        Collections.sort(lottoNumbers);

        return LottoTicket.createAutoTicket(lottoNumbers);
    }

    private static List<LottoNumber> getPossibleNumbers() {
        return possibleNumbers.subList(SUBLIST_FIRST_INDEX, LOTTO_NUM_SIZE);
    }

    public static LottoTicket createManualTickets(List<Integer> lottoNumbers) {
        lottoNumbers.forEach(LottoCreator::checkNullNumber);
        Collections.sort(lottoNumbers);

        return LottoTicket.createManualTicket(lottoNumbers);
    }

    private static void checkNullNumber(Integer number) {
        if (number == null) {
            throw new NullPointerException(ERROR_NULL_LOTTO_NUMBERS);
        }
    }

    public static LottoNumber createBonusBall(Integer bonusBall) {
        return new LottoNumber(bonusBall);
    }
}