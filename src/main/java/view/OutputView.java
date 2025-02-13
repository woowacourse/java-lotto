package view;

import domain.LottoMatch;
import java.util.HashMap;

public class OutputView {
    private final String TOTAL_LOTTO_FORMAT = "%d개를 구매했습니다.\n";
    private final String STATICS_FORMAT = "%s- %d개\n";

    public void displayLottos(int totalLotto, String result) {
        System.out.printf(TOTAL_LOTTO_FORMAT, totalLotto);
        System.out.println(result);
    }

    public void displayResult(HashMap<LottoMatch, Integer> lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for(LottoMatch lottoMatch : lottoResult.keySet()){
            if(lottoMatch == lottoMatch.DEFUALT_MATCH){
                continue;
            }
            System.out.printf(STATICS_FORMAT,lottoMatch.toString(),lottoResult.get(lottoMatch));
        }
    }
}
