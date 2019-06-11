package lottogame.domain;

import lottogame.lottogameexception.InvalidLottoNumberException;
import lottogame.utils.LottoNumbersParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManualLottoGenerator {
    public static Lotto create(String inputLotto) {
        Set<LottoNumber> lottoNumbers;
        try {
            lottoNumbers = getLottoNumbers(LottoNumbersParser.parse(inputLotto));
            checkValidLotto(lottoNumbers);
            return new Lotto(new ArrayList<>(lottoNumbers));
        } catch (NumberFormatException | InvalidLottoNumberException e) {
            System.out.println(e.getMessage());
            return new Lotto(new ArrayList<>());
        }
    }

    private static Set<LottoNumber> getLottoNumbers(List<Integer> parse) {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        for (Integer lottoNumber : parse) {
            lottoNumbers.add(LottoNumber.Of(lottoNumber));
        }
        return lottoNumbers;
    }

    private static void checkValidLotto(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != Lotto.LOTTO_SIZE) {
            throw new InvalidLottoNumberException("6개의 중복되지 않는 숫자를 입력해주세요.");
        }
    }
}
