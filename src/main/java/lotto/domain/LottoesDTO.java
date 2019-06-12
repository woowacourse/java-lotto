package lotto.domain;

import java.util.List;

public class LottoesDTO {
    private static final String JOINING_DELIMITER = "\n";
    private final List<Lotto> lottoes;

    public LottoesDTO(final List<Lotto> lottoes) {
        this.lottoes = lottoes;
    }

    public List<Lotto> getLottoes() {
        return lottoes;
    }
}
