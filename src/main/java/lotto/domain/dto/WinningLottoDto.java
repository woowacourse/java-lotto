package lotto.domain.dto;

import java.util.Objects;

public class WinningLottoDto {
    private int winningLottoId;
    private String winningNumbers;
    private int bonusBall;

    public WinningLottoDto(int winningLottoId, String winningNumbers, int bonusBall) {
        this.winningLottoId = winningLottoId;
        this.winningNumbers = winningNumbers;
        this.bonusBall = bonusBall;
    }

    public int getWinningLottoId() {
        return winningLottoId;
    }

    public String getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusBall() {
        return bonusBall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLottoDto that = (WinningLottoDto) o;
        return winningLottoId == that.winningLottoId &&
                bonusBall == that.bonusBall &&
                Objects.equals(winningNumbers, that.winningNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLottoId, winningNumbers, bonusBall);
    }
}
