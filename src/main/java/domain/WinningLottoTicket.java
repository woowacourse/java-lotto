package domain;

import spark.utils.StringUtils;

import java.util.List;

public class WinningLottoTicket {
    private LottoTicket winningTicket;
    private LottoNumber bonusNumber;

    public WinningLottoTicket(String inputWinningTicket, String inputBonusNumber) {
        validateBlank(inputWinningTicket);
        validateBlank(inputBonusNumber);

        LottoTicket winningTicket = LottoTicketGenerator.createLottoTicket(inputWinningTicket);
        LottoNumber bonusNumber = LottoNumber.getLottoNumber(validateNumber(inputBonusNumber));

        validateDuplicateBonusNumber(winningTicket, bonusNumber);

        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
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

    private void validateDuplicateBonusNumber(LottoTicket winningNumber, LottoNumber bonusNumber) {
        if (winningNumber.containsLottoNumber(bonusNumber)) {
            throw new IllegalArgumentException("중복된 보너스 숫자를 입력하였습니다.");
        }
    }

    public int getCorrectCount(LottoTicket lottoTicket) {
        return Math.toIntExact(lottoTicket.getLottoTicket().stream()
                .filter(this.winningTicket.getLottoTicket()::contains)
                .count());
    }

    public boolean isMatchBonusNumber(LottoTicket lottoTicket) {
        return lottoTicket.containsLottoNumber(bonusNumber);
    }
}
