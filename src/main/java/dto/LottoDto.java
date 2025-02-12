package dto;

import java.util.List;
import model.Lotto;

public class LottoDto {

    private List<Integer> numbers;

    public LottoDto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
