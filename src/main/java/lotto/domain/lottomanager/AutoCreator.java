package lotto.domain.lottomanager;

import lotto.domain.lottomanager.shufflerule.Shuffle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoCreator implements LottoCreator {
    private static final int SUBLIST_FIRST_INDEX = 0;
    private static final int LOTTO_NUM_SIZE = 6;
    private static final String AUTO_CREATOR_SHUFFLE_HAS_NULL = "AutoCreator(Shuffle) has null";

    private Shuffle shuffle;

    public AutoCreator(Shuffle shuffle) {
        if (shuffle == null) {
            throw new IllegalArgumentException(AUTO_CREATOR_SHUFFLE_HAS_NULL);
        }

        this.shuffle = shuffle;
    }

    @Override
    public LottoTicket createTickets() {
        List<LottoNumber> possibleNumbers = LottoNumberManager.getNumbers();
        shuffle.shuffleLottoNumbers(possibleNumbers);
        List<LottoNumber> lottoNumbers = new ArrayList<>(getSixNumbers(possibleNumbers));
        Collections.sort(lottoNumbers);

        return new LottoTicket(lottoNumbers);
    }

    private static List<LottoNumber> getSixNumbers(List<LottoNumber> numbers) {
        return numbers.subList(SUBLIST_FIRST_INDEX, LOTTO_NUM_SIZE);
    }
}
