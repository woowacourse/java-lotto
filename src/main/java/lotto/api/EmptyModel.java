package lotto.api;

import java.util.HashMap;
import java.util.Map;

class EmptyModel {
    private static Map<String, Object> emptyModel;

    private EmptyModel() {  }

    static Map<String, Object> get() {
        if (emptyModel == null) {
            emptyModel = new HashMap<>();
        }
        return emptyModel;
    }
}
