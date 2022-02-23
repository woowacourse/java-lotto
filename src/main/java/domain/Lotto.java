package domain;

import static constant.LottoConstants.LOTTO_NUMBERS_SIZE;
import static constant.LottoConstants.MAXIMUM_LOTTO_NUMBER;
import static constant.LottoConstants.MINIMUM_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    private static final List<LottoNumber> allLottoNumbers = IntStream.range(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
            .boxed()
            .map(LottoNumber::of)
            .collect(Collectors.toList());

    private final List<LottoNumber> chosenNumbers;

    public Lotto() {
        List<LottoNumber> lottoNums = generateRandomLottoNumbers();
        this.chosenNumbers = getSortedLottoNumbers(lottoNums);
    }

    public Lotto(List<LottoNumber> lottoNums) {
        if (lottoNums.size() != LOTTO_NUMBERS_SIZE) {
            throw new RuntimeException();
        }
        this.chosenNumbers = getSortedLottoNumbers(lottoNums);
    }

    private List<LottoNumber> generateRandomLottoNumbers() {
        Collections.shuffle(allLottoNumbers);
        return new ArrayList<>(allLottoNumbers.subList(0, LOTTO_NUMBERS_SIZE));
    }

    private List<LottoNumber> getSortedLottoNumbers(List<LottoNumber> lottoNums) {
        Collections.sort(lottoNums);
        return lottoNums;
    }

    public List<LottoNumber> getChosenNumbers() {
        return chosenNumbers;
    }

    @Override
    public String toString() {
        return "Lotto{" + "numbers=" + chosenNumbers + '}';
    }
}
