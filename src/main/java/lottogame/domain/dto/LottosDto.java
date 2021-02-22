package lottogame.domain.dto;

import java.util.Collections;
import java.util.List;

public class LottosDto {
    private List<LottoDto> lottoDtos;

    public LottosDto(List<LottoDto> lottoDtos) {
        this.lottoDtos = lottoDtos;
    }

    public List<LottoDto> getLottoDtos() {
        return Collections.unmodifiableList(lottoDtos);
    }
}
