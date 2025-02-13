package dto;

import java.util.List;
import model.PrizeTier;

public class LottoDto {

    private List<Integer> numbers;
    private PrizeTier prizeTier;

    public LottoDto(List<Integer> numbers, PrizeTier prizeTier) {
        this.numbers = numbers;
        this.prizeTier = prizeTier;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
