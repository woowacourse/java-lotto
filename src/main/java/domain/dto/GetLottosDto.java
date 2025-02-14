package domain.dto;

import domain.Lottos;
import java.util.List;

public record GetLottosDto(
        List<GetLottoDto> getLottoDtos
) {
    public GetLottosDto(Lottos lottos) {
        this(lottos.getLottos().stream()
                .map(GetLottoDto::new)
                .toList());
    }
}
