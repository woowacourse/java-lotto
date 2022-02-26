package lotto.model.validator;

import java.util.List;
import lotto.model.LottoNumber;

public class Validator {

    public static boolean isValidLength(List<LottoNumber> numbers) {
        return numbers.size() == 6;
    }


    public static boolean isDuplicate(List<LottoNumber> numbers) {
        return numbers.size() != numbers.stream()
                .map(LottoNumber::getLottoNumber)
                .distinct()
                .count();
    }
}
