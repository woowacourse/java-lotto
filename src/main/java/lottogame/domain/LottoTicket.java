package lottogame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {

    public static final int START_LOTTO_NUMBER = 1;
    public static final int FINISH_LOTTO_NUMBER = 45;

    private final LottoNumbers lottoNumbers = new LottoNumbers();

    public LottoTicket() {
        for (int number : getShuffledNumbers(new ArrayList<>())) {
            lottoNumbers.add(new LottoNumber(number + ""));
        }
    }

    private List<Integer> getShuffledNumbers(List<Integer> numbers) {
        Collections.shuffle(initNumbers(numbers));
        return numbers.subList(0, 6);
    }

    public List<Integer> initNumbers(List<Integer> numbers) {
        for (int i = START_LOTTO_NUMBER; i <= FINISH_LOTTO_NUMBER; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers.toList());
    }
}
