function clickHistory() {
    var wantTurn = parseInt($("#turn-input").val())
    var recentTurn = $("#renect-turn").text()

    if (wantTurn > recentTurn) {
        alert("존재하지 않는 회차입니다.")
        return
    }

    var body = {turn:wantTurn}
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
    changeInputArea()
    var dataJson = JSON.parse(data)
    drawWinnerArea(dataJson.winner)
    drawLotteriesArea(dataJson.userLotto)
    $("#history-area").show("slow")
}

function changeInputArea() {
    $("#turn-input").attr("disabled", true)
    $("#turn-btn").attr("disabled", true)
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
    for(var i = 1 ; i <= 6 ; i++) {
        $("#win_"+i).text(lotto[i-1])
    }
    $("#win_bonus").text(bonus)
}

function drawLotteriesArea(lotteries) {
    lotteries.lotteries.forEach(function(lotto){
        drawLottoArea(lotto)
    })
    $("#lotto-number-area").remove()
}

function drawLottoArea(lotto) {
    var lotteriesArea = $("#lotto-number-area")
    
    var clone = lotteriesArea.clone()
    for (var i = 0 ; i < lotto.length ; i++) {
        clone.attr("id", "lotto-number-area" + (i + 1))
        clone.children('button')[i].innerText = lotto[i] + ""
    }
    
    clone.appendTo(lotteriesArea.parent())
}


