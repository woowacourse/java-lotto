package domain;

import spark.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class WinningLottoTicket {
    private static final String DELIMITER = ", ";

    private LottoTicket winningTicket;
    private LottoNumber bonusNumber;

    public WinningLottoTicket(String inputWinningTicket, String inputBonusNumber) {
        validateBlank(inputWinningTicket);
        validateBlank(inputBonusNumber);

        List<LottoNumber> winningTicket = createWinningTicket(inputWinningTicket);
        validateDuplicate(winningTicket, inputBonusNumber);

        this.winningTicket = new LottoTicket(winningTicket);
        this.bonusNumber = new LottoNumber(inputBonusNumber);
    }

    private String[] splitInputNumber(String input) {
        return input.split(DELIMITER);
    }

    private void validateBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("input값이 공백 또는 null입니다.");
        }
    }

    private int validateNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("input값이 숫자가 아닙니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> winningNumber, String inputBonusNumber) {
        int parseNumber = validateNumber(inputBonusNumber);
        if (winningNumber.contains(LottoNumber.getLottoNumber(parseNumber))) {
            throw new IllegalArgumentException("중복된 보너스 숫자를 입력하였습니다.");
        }
    }

    private List<LottoNumber> createWinningTicket(String input) {
        List<LottoNumber> winningTicket = new ArrayList<>();
        for (String number : splitInputNumber(input)) {
            validateBlank(number);
            winningTicket.add(LottoNumber.getLottoNumber(validateNumber(number)));
        }
        return winningTicket;
    }

    public int getCorrectCount(LottoTicket lottoTicket) {
        return Math.toIntExact(lottoTicket.getLottoTicket().stream()
                .filter(this.winningTicket.getLottoTicket()::contains)
                .count());
    }

    public boolean isMatchBonusNumber(LottoTicket lottoTicket) {
        return lottoTicket.getLottoTicket()
                .contains(bonusNumber);
    }
}
