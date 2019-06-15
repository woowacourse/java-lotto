package lotto.controller;

import spark.Route;

public class IndexController {
    public static Route serveIndexPage = (request, response) -> {
        response.redirect("/index.html");
        return null;
    };
}