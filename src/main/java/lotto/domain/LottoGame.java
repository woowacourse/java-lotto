package lotto.domain;

public class LottoGame {
    private final WinningInformation winningInformation;

    public LottoGame(final WinningInformation winningInformation) {
        this.winningInformation = winningInformation;
    }

    public LottoResult play(Lottos lottos) {
        LottoResult lottoResult = new LottoResult();
        for (Lotto lotto : lottos.getLottos()) {
            lottoResult.add(winningInformation.match(lotto));
        }
        return lottoResult;
    }
}