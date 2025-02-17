package Model;

import java.util.Comparator;
import java.util.List;

public class Lotto {

    private final List<Integer> lottoNumber;

    public Lotto(List<Integer> lottoNumber) {
        lottoNumber.sort(Comparator.naturalOrder());
        this.lottoNumber = lottoNumber;
    }

    public String printLottoNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        combineLottoNumber(sb);
        sb.append("]");

        return sb.toString();
    }

    private void combineLottoNumber(StringBuilder sb) {
        for (int i : lottoNumber) {
            sb.append(i);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }
}
