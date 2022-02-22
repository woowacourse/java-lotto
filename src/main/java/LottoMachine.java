import java.util.List;

public class LottoMachine {
    private final LottoRankJudge lottoRankJudge;

    public LottoMachine(LottoRankJudge lottoRankJudge) {
        this.lottoRankJudge = lottoRankJudge;
    }

    public LottoResult getResult(List<List<Integer>> lottoNumbersList) {
        LottoResult result = new LottoResult();

        for (List<Integer> lottoNumbers : lottoNumbersList) {
            LottoRank rank = lottoRankJudge.judge(lottoNumbers);
            result.add(rank);
        }

        return result;
    }
}
