package domain.numberscontainer;

import domain.LottoRank;

public class WinningNumbers extends LottoNumbers {

    private final LottoNumber bonusNumber;

    public WinningNumbers(String lottoNumbers, int bonusNumber) {
        super(lottoNumbers);
        validateBonusNumber(bonusNumber);
        this.bonusNumber = LottoNumber.get(bonusNumber);
    }

    private void validateBonusNumber(int bonusNumber) {
        if (this.lottoNumbers.contains(LottoNumber.get(bonusNumber))) {
            throw new IllegalArgumentException("당첨 번호와 중복되지 않는 보너스 번호를 입력해주세요.");
        }
    }

    public LottoRank getLottoRank(Ticket ticket) {
        int matchCount = findDuplicatedNumbers(ticket);
        boolean isBonus = ticket.contains(bonusNumber);
        return LottoRank.findLottoRank(matchCount, isBonus);
    }

    public int findDuplicatedNumbers(Ticket ticket) {
        return (int) this.lottoNumbers.stream()
                .filter(ticket::contains)
                .count();
    }
}