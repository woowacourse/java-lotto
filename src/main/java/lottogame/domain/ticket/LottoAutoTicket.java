package lottogame.domain.ticket;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoAutoTicket implements LottoTicket {

    private final LottoNumbers lottoNumbers;

    public LottoAutoTicket() {
        List<LottoNumber> lottoNumberGroup = new ArrayList<>();
        for (int number : getShuffledNumbers(new ArrayList<>())) {
            lottoNumberGroup.add(new LottoNumber(number));
        }
        lottoNumbers = new LottoNumbers(lottoNumberGroup);
    }

    private List<Integer> getShuffledNumbers(final List<Integer> numbers) {
        Collections.shuffle(initNumbers(numbers));

        List<Integer> shuffledNumbers = numbers.subList(0, COUNT_LOTTO_NUMBER);
        return shuffledNumbers;
    }

    private List<Integer> initNumbers(final List<Integer> numbers) {
        for (int i = START_LOTTO_NUMBER; i <= FINISH_LOTTO_NUMBER; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    @Override
    public List<LottoNumber> getLottoNumbers() {
        return new ArrayList<>(lottoNumbers.toList());
    }

    @Override
    public boolean isContainNumber(final LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    @Override
    public boolean isAutoTicket() {
        return true;
    }
}
