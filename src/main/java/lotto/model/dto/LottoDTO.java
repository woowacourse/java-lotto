package lotto.model.dto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.Lotto;
import lotto.model.Lottos;

public class LottoDTO {
    private final List<Integer> numbers;

    private LottoDTO(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static List<LottoDTO> from(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(lotto -> new LottoDTO(getValuesFrom(lotto)))
                .collect(Collectors.toList());
    }

    private static List<Integer> getValuesFrom(Lotto lotto) {
        return lotto.getNumbers().getValues();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
