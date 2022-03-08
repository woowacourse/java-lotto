package service.dto;

public class WinningLottoNumbersDto {
    private LottoDto winningLotto;
    private int bonusNumber;

    public WinningLottoNumbersDto(LottoDto winningLotto, int bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public LottoDto getWinningLotto() {
        return winningLotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
