var realrank

function clickHistory() {
    var body = {turn:$("#renect-turn").text()}
    var queryString = JSON.stringify(body)
    $.ajax({
        type: "POST",
        url: "/showHistory",
        data : queryString,
        contentType : 'application/json;charset=UTF-8;version=1.0',

        error: function(xhr, status, error){
            alert(error)
        },
        success: showHistoryArea
    })
}

function showHistoryArea(data) {
    var dataJson = JSON.parse(data)
    drawWinnerArea(dataJson.winner)
    drawLotteriesArea(dataJson.userLotto)
    $("#history-area").show("slow")
}

function drawWinnerArea(data) {
    drawWantTurnText(data.turn)
    drawResultArea(data.profit, data.rank)
    drawWinLottoArea(data.winLotto)
}

function drawResultArea(profit, rank) {
    drawProfitArea(profit)
    drawRankArea(rank)
}

function drawWantTurnText(turn) {
    $("#want-turn-text").text(turn)
}

function drawProfitArea(profit) {
    $("#profit-text").text(profit)
}

function drawRankArea(rank) {
    for (var i = 1 ; i <= 5 ; i++) {
        $("#rank_" + i).text(rank[i-1][i])
    }
}

function drawWinLottoArea(winLotto) {
    var lotto = winLotto.lotto
    var bonus = winLotto.bonus
}

function drawLotteriesArea(lotteries) {

}

