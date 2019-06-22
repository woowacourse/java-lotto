package lotto.domain;

import java.util.*;

public class RandomLottoNumbersGenerator implements LottoNumbersGenerator {
    @Override
    public List<LottoNumber> generateNumbers() {
        List<LottoNumber> possibleNumbers = new ArrayList<>();
        for (int i = LottoNumber.getMinLottoNumber(); i <= LottoNumber.getMaxLottoNumber(); i++) {
            possibleNumbers.add(LottoNumber.valueOf(i));
        }
        Collections.shuffle(possibleNumbers);
        possibleNumbers = possibleNumbers.subList(0, Lotto.getSizeOfLottoNumbers());
        return possibleNumbers;
    }
}
