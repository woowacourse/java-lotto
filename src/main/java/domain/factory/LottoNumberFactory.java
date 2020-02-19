package domain.factory;

import domain.LottoNumber;
import domain.numberscontainer.LottoNumbersDto;

import java.util.*;

public class LottoNumberFactory {

    private static final int FIRST_INDEX = 0;
    private static final int SIXTH_INDEX = 6;
    private static final int EXCEPT_ERROR_FIRST_INDEX = 1;

    public LottoNumbersDto generateRandomTicketDto() {
        List<LottoNumber> lottoNumbers = getShuffledList();
        Set<LottoNumber> sixNumbers = new HashSet<>(lottoNumbers.subList(FIRST_INDEX, SIXTH_INDEX));

        return new LottoNumbersDto(sixNumbers);
    }

    private List<LottoNumber> getShuffledList() {
        List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumber.values()).subList(EXCEPT_ERROR_FIRST_INDEX, LottoNumber.values().length);
        Collections.shuffle(lottoNumbers);
        return lottoNumbers;
    }

    public LottoNumbersDto generateFixedNumber(Set<LottoNumber> sixNumbers, LottoNumber bonusNumber) {
        return new LottoNumbersDto(sixNumbers, bonusNumber);
    }
}