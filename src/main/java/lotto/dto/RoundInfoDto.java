package lotto.dto;

public class RoundInfoDto {
    private final String winNumber;
    private final String bonusNumber;
    private final String userLottoString;
    private final int round;
    private final int lottoRound;

    public RoundInfoDto(String winNumber, String bonusNumber, String userLottoString, int round, int lottoRound) {
        this.winNumber = winNumber;
        this.bonusNumber = bonusNumber;
        this.userLottoString = userLottoString;
        this.round = round;
        this.lottoRound = lottoRound;
    }

    public String getWinNumber() {
        return winNumber;
    }

    public String getBonusNumber() {
        return bonusNumber;
    }

    public String getUserLottoString() {
        return userLottoString;
    }

    public int getRound() {
        return round;
    }

    public int getLottoRound() {
        return lottoRound;
    }
}
