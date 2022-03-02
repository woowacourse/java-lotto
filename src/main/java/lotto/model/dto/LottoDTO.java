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
                .map(LottoDTO::from)
                .collect(Collectors.toList());
    }

    private static LottoDTO from(Lotto lotto) {
        return new LottoDTO(lotto.getValues());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
