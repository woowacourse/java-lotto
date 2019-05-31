package lotto.domain;

public class WinningLottoDto {
    private final String numbers;
    private final String bonus;

    public WinningLottoDto(String numbers, String bonus) {
        this.numbers = numbers;
        this.bonus = bonus;
    }

    public String numbers() {
        return numbers;
    }

    public String bonus() {
        return bonus;
    }
}
