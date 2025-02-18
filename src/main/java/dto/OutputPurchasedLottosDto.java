package dto;

import java.util.List;
import validator.LottoValidator;

public record OutputPurchasedLottosDto(List<Integer> lottos) {
    public OutputPurchasedLottosDto(List<Integer> lottos) {
        LottoValidator.validate(lottos);
        this.lottos = lottos;
    }
}
