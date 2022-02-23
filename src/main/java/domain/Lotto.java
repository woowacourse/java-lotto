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
        Collections.shuffle(allLottoNumbers);
        List<LottoNumber> lottoNums = new ArrayList<>(allLottoNumbers.subList(0, 6));
        Collections.sort(lottoNums);

        this.chosenNumbers = lottoNums;
    }

    public Lotto(List<LottoNumber> lottoNums) {
        if (lottoNums.size() != LOTTO_NUMBERS_SIZE) {
            throw new RuntimeException();
        }
        Collections.sort(lottoNums);
        this.chosenNumbers = lottoNums;
    }

    public List<LottoNumber> getChosenNumbers() {
        return chosenNumbers;
    }

    @Override
    public String toString() {
        return "Lotto{" + "numbers=" + chosenNumbers + '}';
    }
}
