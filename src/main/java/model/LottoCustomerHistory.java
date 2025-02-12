package model;

import java.util.ArrayList;
import java.util.List;

public class LottoCustomerHistory {

    private static final LottoCustomerHistory INSTANCE = new LottoCustomerHistory();

    private final List<LottoCustomer> customers = new ArrayList<LottoCustomer>();

    private LottoCustomerHistory() {
    }

    public static LottoCustomerHistory getInstance() {
        return INSTANCE;
    }

    public void add(LottoCustomer lottoCustomer) {
        customers.add(lottoCustomer);
    }
}

