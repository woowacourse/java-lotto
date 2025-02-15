package util;

import java.util.function.Supplier;

public class RetryHandler {
    public static <T> Object retryUntilSuccessWithReturn(Supplier<T> supplier) {
        ResolveResult rr;
        do {
            rr = tryGivenMethod(supplier);
        } while (rr.isSuccess());
        return rr.getResult();
    }

    private static <T> ResolveResult tryGivenMethod(Supplier<T> supplier) {
        try {
            return new ResolveResult(supplier.get(), true);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return new ResolveResult(null, false);
    }
}


class ResolveResult {
    private final Object result;
    private final boolean isSuccess;

    public ResolveResult(Object result, boolean isSuccess) {
        this.result = result;
        this.isSuccess = isSuccess;
    }

    public Object getResult() {
        return result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
