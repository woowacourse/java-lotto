package util;

import domain.numberscontainer.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;

public class SixLottoNumbersFactory {
    private static final int FIRST_INDEX = 0;
    private static final int SIXTH_INDEX = 6;

    public static Set<LottoNumber> createRandom() {
        Set<LottoNumber> sixNumbers = new HashSet<>(getShuffledList().subList(FIRST_INDEX, SIXTH_INDEX));

        return sixNumbers;
    }

    private static List<LottoNumber> getShuffledList() {
        List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumber.values());
        Collections.shuffle(lottoNumbers);

        return lottoNumbers;
    }

    public static Set<LottoNumber> createFixed(Set<Integer> givenNumbers) {
        Set<LottoNumber> sixNumbers = givenNumbers.stream()
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toSet());
        return sixNumbers;
    }
}