package model;

import java.util.Arrays;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public LottoDTO getLottoDTO() {
        return new LottoDTO(numbers);
    }

    public void compare(LottoWinningNumberDTO winningNumberDTO) {
        List<Integer> winningNumbers = winningNumberDTO.getWinningNumbers();
        long count = numbers.stream()
                .filter(winningNumbers::contains).count();

        Arrays.stream(Statistics.values())
                .filter(statistics -> statistics.getMatchNumber() == count)
                .forEach(Statistics::addCount);
    }
}
