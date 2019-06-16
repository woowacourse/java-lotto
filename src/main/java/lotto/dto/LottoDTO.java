package lotto.dto;

public class LottoDTO {
    private String round;
    private String numbers;

    public LottoDTO(String round, String numbers) {
        this.round = round;
        this.numbers = numbers;
    }

    public String getRound() {
        return round;
    }

    public String getNumbers() {
        return numbers;
    }
}
