package configure;

import controller.LottoController;

public class Configure {
    private static LottoController lottoController;

    public LottoController lottoController() {
        if (lottoController == null) {
            return new LottoController();
        }
        return lottoController;
    }
}
