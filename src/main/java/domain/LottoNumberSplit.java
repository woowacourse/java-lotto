package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNumberSplit {
    private static final String DELIMITER = ",";

    private LottoNumberSplit() {
    }

    public static List<LottoNumber> initializeLottoNumbers(String input) {
        String[] numbers = input.split(DELIMITER);
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        try {
            getLottoNumber(numbers, lottoNumbers);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 숫자를 입력하였습니다.");
        }
        return lottoNumbers;
    }

    private static void getLottoNumber(String[] numbers, List<LottoNumber> lottoNumbers) {
        for (int i = 0; i < numbers.length; i++) {
            int lottoNumber = Integer.parseInt(numbers[i].trim());
            lottoNumbers.add(LottoNumbers.getLottoNumber(lottoNumber));
        }
    }
}
