package lotto.domain.lottomanager;

import lotto.domain.lottomanager.shufflerule.Shuffle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoCreator implements LottoCreator {
    private static final int SUBLIST_FIRST_INDEX = 0;
    private static final int LIMIT_MIN_NUM = 1;
    private static final int LIMIT_MAX_NUM = 45;
    private static final int LOTTO_NUM_SIZE = 6;
    private static final String AUTO_CREATOR_SHUFFLE_HAS_NULL = "AutoCreator(Shuffle) has null";

    private static List<LottoNumber> possibleNumbers;
    private Shuffle shuffle;

    static {
        possibleNumbers = IntStream.rangeClosed(LIMIT_MIN_NUM, LIMIT_MAX_NUM)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public AutoCreator(Shuffle shuffle) {
        if (shuffle == null) {
            throw new IllegalArgumentException(AUTO_CREATOR_SHUFFLE_HAS_NULL);
        }

        this.shuffle = shuffle;
    }

    @Override
    public LottoTicket createTickets() {
        shuffle.shuffleLottoNumbers(possibleNumbers);
        List<LottoNumber> lottoNumbers = new ArrayList<>(getSixNumbers());
        Collections.sort(lottoNumbers);

        return new LottoTicket(lottoNumbers);
    }

    private List<LottoNumber> getSixNumbers() {
        return possibleNumbers.subList(SUBLIST_FIRST_INDEX, LOTTO_NUM_SIZE);
    }
}
