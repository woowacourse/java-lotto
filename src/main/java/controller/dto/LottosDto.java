package controller.dto;

import domain.Lottos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottosDto {
    private final List<LottoDto> lottoDtos;
    private final int size;

    public LottosDto(List<LottoDto> lottoDtos, int size) {
        this.lottoDtos = new ArrayList<>(lottoDtos);
        this.size = size;
    }

    public List<LottoDto> getLottoDtos() {
        return lottoDtos;
    }

    public int getSize() {
        return size;
    }

    public static LottosDto from(Lottos lottos, int size) {
        return lottos.getLottos()
                .stream()
                .map(LottoDto::from)
                .collect(Collectors.collectingAndThen(
                        Collectors.toUnmodifiableList(),
                        lottoDtos -> new LottosDto(lottoDtos, size)
                ));
    }
}
