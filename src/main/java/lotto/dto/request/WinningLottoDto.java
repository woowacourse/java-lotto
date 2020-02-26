package lotto.dto.request;

import java.util.Set;

public class WinningLottoDto {

    private final Set<Integer> winningLottoNumbers;
    private final Integer bonusNumber;

    public WinningLottoDto(Set<Integer> winningLottoNumbers, int bonusNumber) {
        this.winningLottoNumbers = winningLottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Set<Integer> getWinningLottoNumbers() {
        return winningLottoNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
