package domain.dto;

import java.util.List;

public record GetLottosDto(
        List<GetLottoDto> getLottoDtos
) {
}
