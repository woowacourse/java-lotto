package controller;

import spark.Response;
import view.WebView;

public class Error {
    public static String orThrow(SupplierWithException<String, Exception> f, Response res) {
        try {
            return f.get();
        } catch (Exception e) {
            res.status(500);
            return WebView.error();
        }
    }
}