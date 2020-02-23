package domain;

import spark.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class WinningLottoTicket {
    private static final String DELIMITER = ",";

    private LottoTicket winningLottoTicket;

    public WinningLottoTicket(String input) {
        validateBlank(input);
        List<LottoNumber> lottoNumbers = initializeLottoNumbers(input);
        this.winningLottoTicket = new LottoTicket(lottoNumbers);
    }

    private void validateBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("입력값이 없습니다.");
        }
    }

    private List<LottoNumber> initializeLottoNumbers(String input) {
        String[] numbers = input.split(DELIMITER);
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        try {
            getLottoNumber(numbers, lottoNumbers);
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 숫자를 입력하였습니다.");
        }
        return lottoNumbers;
    }

    private void getLottoNumber(String[] numbers, List<LottoNumber> lottoNumbers) {
        for (int i = 0; i < numbers.length; i++) {
            lottoNumbers.add(new LottoNumber(Integer.parseInt(numbers[i])));
        }
    }
}
