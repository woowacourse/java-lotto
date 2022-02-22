package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicket {
    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(GenerateStrategy generateStrategy) {
        lottoNumbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lottoNumbers.add(new LottoNumber(generateStrategy));
        }
    }
}
