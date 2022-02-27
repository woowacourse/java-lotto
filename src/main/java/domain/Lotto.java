package domain;

import static validator.NumberValidators.validateLottoNumbersSize;

import java.util.Collections;
import java.util.List;

public class Lotto {

    public static final int PRICE = 1000;
    public static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<LottoNumber> chosenNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateLottoNumbersSize(lottoNumbers);
        this.chosenNumbers = getSortedLottoNumbers(lottoNumbers);
    }

    private List<LottoNumber> getSortedLottoNumbers(List<LottoNumber> lottoNumbers) {
        Collections.sort(lottoNumbers);
        return Collections.unmodifiableList(lottoNumbers);
    }

    public List<LottoNumber> getChosenNumbers() {
        return chosenNumbers;
    }

    @Override
    public String toString() {
        return "Lotto{" + "numbers=" + chosenNumbers + '}';
    }
}
