package lotto.dto;

import lotto.domain.LottoPaper;

import java.util.List;
import java.util.stream.Collectors;

public class LottoPaperDto {
    private String round;
    private List<LottoDto> lottoDtos;

    public LottoPaperDto(String round, LottoPaper lottoPaper) {
        this.round = round;
        lottoDtos = lottoPaper.getLottos()
                .stream()
                .map(lotto -> new LottoDto(round, lotto))
                .collect(Collectors.toList());
    }

    public String getRound() {
        return round;
    }

    public List<LottoDto> getLottoDtos() {
        return lottoDtos;
    }
}
