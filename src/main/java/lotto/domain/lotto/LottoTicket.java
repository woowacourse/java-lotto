package lotto.domain.lotto;

import java.util.*;

public class LottoTicket {
    public static final int PRICE = 1000;

    private final LottoNumberGroup lottoNumbers;

    private LottoTicket(LottoNumberGroup lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket create(String lottoNumbersText) {
        return new LottoTicket(LottoNumberGroup.create(lottoNumbersText));
    }

    public static LottoTicket create() {
        return new LottoTicket(LottoNumberGroup.create());
    }

    public int countOfMatch(LottoNumberGroup lottoNumbers) {
        return lottoNumbers.countOfMatch(this.lottoNumbers);
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket lotto = (LottoTicket) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    @Override
    public LottoTicket clone() {
        return new LottoTicket(lottoNumbers.clone());
    }
}
