package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNumbers {

    private List<LottoNumber> lottoNumbers = new ArrayList<>();

    public LottoNumbers(List<Integer> numbers) {
        numbers.forEach(number -> lottoNumbers.add(new LottoNumber(number)));
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

}
