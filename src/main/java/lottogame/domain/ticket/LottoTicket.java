package lottogame.domain.ticket;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {

    private static final int START_LOTTO_NUMBER = 1;
    private static final int FINISH_LOTTO_NUMBER = 45;
    private static final int COUNT_LOTTO_NUMBER = 6;

    private final LottoNumbers lottoNumbers;
    private final boolean isAuto;


    private LottoTicket(final LottoNumbers lottoNumbers, final boolean isAuto) {
        this.lottoNumbers = new LottoNumbers(lottoNumbers.toList());
        this.isAuto = isAuto;
    }

    public static LottoTicket of() {
        return new LottoTicket(getAutoNumbers(), true);
    }

    public static LottoTicket of(final LottoNumbers lottoNumbers) {
        return new LottoTicket(lottoNumbers, false);
    }

    private static LottoNumbers getAutoNumbers() {
        List<LottoNumber> lottoNumberGroup = new ArrayList<>();
        for (int number : getShuffledNumbers()) {
            lottoNumberGroup.add(LottoNumber.of(number));
        }
        return new LottoNumbers(lottoNumberGroup);
    }

    private static List<Integer> getShuffledNumbers() {
        List<Integer> numbers = new ArrayList<>();
        Collections.shuffle(initNumbers(numbers));

        return numbers.subList(0, COUNT_LOTTO_NUMBER);
    }

    private static List<Integer> initNumbers(final List<Integer> numbers) {
        for (int i = START_LOTTO_NUMBER; i <= FINISH_LOTTO_NUMBER; i++) {
            numbers.add(i);
        }
        return numbers;
    }


    public List<LottoNumber> getLottoNumbers() {
        return new ArrayList<>(lottoNumbers.toList());
    }

    public boolean isContainNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public boolean isAutoTicket() {
        return isAuto;
    }
}
