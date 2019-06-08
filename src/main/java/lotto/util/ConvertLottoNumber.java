package lotto.util;

import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertLottoNumber {

    public static List<LottoNumber> run(String numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (String number : Arrays.asList(numbers.split(","))) {
            try {
                lottoNumbers.add(LottoNumber.getInstance(Integer.parseInt(number)));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("로또 번호는 숫자를 입력해야 합니다.");
            }
        }
        return lottoNumbers;
    }
}