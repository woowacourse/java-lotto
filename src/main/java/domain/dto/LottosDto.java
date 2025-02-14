package domain.dto;

import domain.Lottos;
import java.util.List;

public record LottosDto(
        List<LottoDto> getLottoDtos
) {
    public LottosDto(Lottos lottos) {
        this(lottos.getLottos().stream()
                .map(LottoDto::new)
                .toList());
    }
}
