package domain;

public class WinningLottoTicket {
    private LottoTicket winningTicket;
    private LottoNumber bonusNumber;

    public WinningLottoTicket(String inputWinningTicket, String inputBonusNumber) {
        LottoTicket winningTicket = LottoTicketGenerator.createLottoTicket(inputWinningTicket);
        LottoNumber bonusNumber = LottoNumber.from(inputBonusNumber);

        validateDuplicateBonusNumber(winningTicket, bonusNumber);

        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
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
