package domain.dto;

import domain.Lottos;
import java.util.List;

public record LottosDto(
        List<LottoDto> getLottoDtos
) {
    public static LottosDto from(Lottos lottos){
        return new LottosDto(lottos.getLottos().stream()
                .map(LottoDto::new)
                .toList());
    }
}
