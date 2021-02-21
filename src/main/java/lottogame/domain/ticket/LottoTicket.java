package lottogame.domain.ticket;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {

    public static final int START_LOTTO_NUMBER = 1;
    public static final int FINISH_LOTTO_NUMBER = 45;
    public static final int COUNT_LOTTO_NUMBER = 6;

    private final LottoNumbers lottoNumbers;

    public LottoTicket() {
        List<LottoNumber> lottoNumberGroup = new ArrayList<>();
        for (int number : getShuffledNumbers(new ArrayList<>())) {
            lottoNumberGroup.add(new LottoNumber(number));
        }
        lottoNumbers = new LottoNumbers(lottoNumberGroup);
    }

    private List<Integer> getShuffledNumbers(List<Integer> numbers) {
        Collections.shuffle(initNumbers(numbers));

        List<Integer> shuffledNumbers = numbers.subList(0, COUNT_LOTTO_NUMBER);
        Collections.sort(shuffledNumbers);
        return shuffledNumbers;
    }

    private List<Integer> initNumbers(List<Integer> numbers) {
        for (int i = START_LOTTO_NUMBER; i <= FINISH_LOTTO_NUMBER; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers.toList());
    }
}
