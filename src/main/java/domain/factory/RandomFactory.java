package domain.factory;

import domain.LottoNumber;
import domain.numberscontainer.LottoNumbersDto;

import java.util.*;

public class RandomFactory implements LottoNumbersDtoFactory{

    private static final int FIRST_INDEX = 0;
    private static final int SIXTH_INDEX = 6;
    private static final int EXCEPT_ERROR_FIRST_INDEX = 1;

    @Override
    public LottoNumbersDto generate(boolean containsBonus) {
        List<LottoNumber> lottoNumbers = getShuffledList();

        Set<LottoNumber> sixNumbers = new HashSet<>(lottoNumbers.subList(FIRST_INDEX, SIXTH_INDEX));
        LottoNumber bonus = lottoNumbers.get(SIXTH_INDEX);

        if (containsBonus) {
            return new LottoNumbersDto(sixNumbers, bonus);
        }
        return new LottoNumbersDto(sixNumbers);
    }

    private List<LottoNumber> getShuffledList() {
        List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumber.values()).subList(EXCEPT_ERROR_FIRST_INDEX, LottoNumber.values().length);
        Collections.shuffle(lottoNumbers);
        return lottoNumbers;
    }
}
