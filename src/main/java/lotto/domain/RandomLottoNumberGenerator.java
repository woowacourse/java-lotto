package lotto.domain;

import java.util.*;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {
    @Override
    public List<Integer> generateNumbers() {
        List<Integer> possibleNumbers = new ArrayList<>();
        for (int i=LottoNumber.getMinLottoNumber(); i <= LottoNumber.getMaxLottoNumber(); i++) {
            possibleNumbers.add(i);
        }
        Collections.shuffle(possibleNumbers);
        possibleNumbers = possibleNumbers.subList(0, Lotto.getSizeOfLottoNumbers());
        return possibleNumbers;
    }
}
