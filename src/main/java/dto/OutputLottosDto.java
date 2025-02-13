package dto;

import java.util.List;
import validator.LottoValidator;

public record OutputLottosDto(List<Integer> lottos) {
    public OutputLottosDto(List<Integer> lottos) {
        LottoValidator.validate(lottos);
        this.lottos = lottos;
    }

    private static void checkSort() {

    }
}
