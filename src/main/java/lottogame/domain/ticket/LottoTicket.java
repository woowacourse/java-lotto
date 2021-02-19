package lottogame.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;

public class LottoTicket {

    private static final int START_LOTTO_NUMBER = 1;
    private static final int FINISH_LOTTO_NUMBER = 45;

    private final LottoNumbers lottoNumbers;

    public LottoTicket() {
        this.lottoNumbers = new LottoNumbers();
        for (int number : issueLottoNumbers(new ArrayList<>())) {
            lottoNumbers.add(new LottoNumber(number));
        }
    }

    private List<Integer> issueLottoNumbers(final List<Integer> numbers) {
        Collections.shuffle(initNumbers(numbers));
        List<Integer> shuffledNumbers = numbers.subList(0, 6);
        Collections.sort(shuffledNumbers);
        return shuffledNumbers;
    }

    private List<Integer> initNumbers(final List<Integer> numbers) {
        for (int number = START_LOTTO_NUMBER; number <= FINISH_LOTTO_NUMBER; number++) {
            numbers.add(number);
        }
        return numbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers.toList());
    }
}
