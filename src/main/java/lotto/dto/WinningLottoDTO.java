package lotto.dto;

public class WinningLottoDTO {
    private String round;
    private String numbers;
    private String bonusNumber;

    public WinningLottoDTO(String round, String numbers, String bonusNumber) {
        this.round = round;
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public String getRound() {
        return round;
    }

    public String getNumbers() {
        return numbers;
    }

    public String getBonusNumber() {
        return bonusNumber;
    }
}
