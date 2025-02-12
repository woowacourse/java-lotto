package Service;

import Model.LottoResult;

public class LottoService {

    public int lottoCount(int price){
        return price / 1000;
    }

    public double lottoRateOfReturn(int price) {
        double result = (double) LottoResult.calculateWinnings() / price;
        result = Math.floor(result * 100);
        result /= 100;
        return result;
    }
}
