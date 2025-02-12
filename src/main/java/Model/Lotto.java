package Model;

import java.util.List;

public class Lotto {

    private List<Integer> lottoNumber;

    public Lotto(List<Integer> lottoNumber){
        this.lottoNumber = lottoNumber;
    }

    public String printLottoNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i : lottoNumber){
           sb.append(i);
           sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");

        return sb.toString();
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }
}
