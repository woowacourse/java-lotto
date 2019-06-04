package lotto;

import lotto.exception.InvalidLottoNumbersException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinningLotto {
    private Lotto lotto;

    public WinningLotto(String input) {
        checkIsBlank(input);
        List<Integer> numbers = toInt(Arrays.asList(input.split(",")));
        Lotto lotto = new Lotto(LottoNumber.getLottoNumbers(numbers));
        this.lotto = lotto;
    }

    private void checkIsBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new InvalidLottoNumbersException("아무것도 입력하지 않으셨습니다.");
        }
    }

    private List<Integer> toInt(List<String> strings) {
        List<Integer> numbers = new ArrayList<>();

        for (String string : strings) {
            numbers.add(parseInt(string));
        }
        return numbers;
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidLottoNumbersException("숫자자 아닌 값이 포함되어 있습니다.");
        }
    }
}
