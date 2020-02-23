package domain;

import spark.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class WinningLottoTicket {
    private static final String DELIMITER = ",";

    private LottoTicket winningLottoTicket;
    private BonusBall bonusBall;

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
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 숫자를 입력하였습니다.");
        }
        return lottoNumbers;
    }

    private void getLottoNumber(String[] numbers, List<LottoNumber> lottoNumbers) {
        for (int i = 0; i < numbers.length; i++) {
            lottoNumbers.add(new LottoNumber(Integer.parseInt(numbers[i].trim())));
        }
    }

    public void initializeBonusBall(int bonusNumber) {
        if (this.winningLottoTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 로또 숫자는 당첨 숫자와 중복될 수 없습니다.");
        }
        this.bonusBall = new BonusBall(bonusNumber);
    }
}
