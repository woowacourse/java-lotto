package lotto.domain;

import lotto.utils.CustomException;

public class WinningNumbers {
    private final LottoTicket lottoTicket;
    private final LottoNumber bonusBall;

    public WinningNumbers(String lottoNumbersValue, String bonusBallValue) {
        LottoTicket lottoTicket = new LottoTicket(lottoNumbersValue);
        LottoNumber bonusBall = LottoNumber.valueOf(bonusBallValue);

        validateDuplication(lottoTicket, bonusBall);

        this.lottoTicket = lottoTicket;
        this.bonusBall = bonusBall;
    }

    private void validateDuplication(LottoTicket lottoTicket, LottoNumber bonusBall) {
        if (lottoTicket.contains(bonusBall)) {
            throw new CustomException("보너스볼이 당첨번호와 중복됩니다.");
        }
    }

    private int countMatches(LottoTicket lottoTicket) {
        return (int) lottoTicket.getUnmodifiableList().stream()
            .filter(this.lottoTicket::contains)
            .count();
    }

    public Rank getRank(LottoTicket lottoTicket) {
        return Rank.getInstance(countMatches(lottoTicket), lottoTicket.contains(bonusBall));
    }

}
