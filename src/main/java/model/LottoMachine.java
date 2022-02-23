package model;

import java.util.List;

public class LottoMachine {
    private final LottoRankJudge lottoRankJudge;

    public LottoMachine(LottoRankJudge lottoRankJudge) {
        this.lottoRankJudge = lottoRankJudge;
    }

    public LottoResult summarize(List<LottoNumbers> lottoNumbersList) {
        LottoResult result = new LottoResult();

        for (LottoNumbers lottoNumbers : lottoNumbersList) {
            LottoRank rank = lottoRankJudge.judge(lottoNumbers);
            result.add(rank);
        }

        return result;
    }
}
