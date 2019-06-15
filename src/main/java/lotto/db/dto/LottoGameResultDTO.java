package lotto.db.dto;

public class LottoGameResultDTO {
    private int winningLottoId;
    private String winningNumbers;
    private int bonusBall;

    public LottoGameResultDTO(int winningLottoId, String winningNumbers, int bonusBall) {
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
}
