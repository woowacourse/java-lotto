package lotto;

import java.util.List;
import java.util.stream.Collectors;

public class LottoDTO {

    private List<Integer> numbers;

    private LottoDTO(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static List<LottoDTO> from(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(lotto -> new LottoDTO(lotto.getNumbers()))
                .collect(Collectors.toList());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
