package lotto.domain;

public interface LottoTicket {
    int size();

    int includedNumber(Lotto lotto);

    boolean contains(LottoNo lottoNo);
}
