function isValidInput() {
    var price = document.getElementById('price').value;
    var manualCount = document.getElementById('manualCount').value;

    if (price < 1000 || price > 100000) {
        window.alert("로또는 1,000원 이상 100,000원 이하만 구매 가능 합니다.");
        document.getElementById('price').value = document.getElementById('manualCount').value = '';
    } else {
        document.getElementById('validCheck').innerHTML = '';
    }
    if (document.getElementById('price').value / 1000 < document.getElementById('manualCount').value) {
        document.getElementById('manualCountMessage').innerHTML = '총 구매 개수보다 많습니다.';
        document.getElementById('manualCountMessage').style.color = 'red';
        document.getElementById('manualCount').value = '';
        delFields();
    } else {
        document.getElementById('manualCountMessage').innerHTML = '구매 가능합니다.';
        document.getElementById('manualCountMessage').style.color = 'blue';
        addFields();
    }
}

function initField() {
    document.getElementById('price').value = document.getElementById('manualCount').value = '';
    delFields();
}

function addFields() {

    var number = document.getElementById("manualCount").value;
    var manualLottoDiv = document.getElementById("manualLottoDiv");
    while (manualLottoDiv.hasChildNodes()) {
        manualLottoDiv.removeChild(manualLottoDiv.lastChild);
    }
    for (i = 0; i < number; i++) {
        var tmpContainer = createContainer(i);
        tmpContainer.appendChild(makeText(i));
        var input = document.createElement("input");
        input.type = "text";
        input.name = "lotto" + i;
        input.className = "form-control";
        tmpContainer.appendChild(input);
        tmpContainer.appendChild(document.createElement("br"));
        manualLottoDiv.appendChild(tmpContainer);
    }
}

function delFields() {
    var manualLottoDiv = document.getElementById("manualLottoDiv");
    while (manualLottoDiv.hasChildNodes()) {
        manualLottoDiv.removeChild(manualLottoDiv.lastChild);
    }
}

function createContainer(i) {
    var div = document.createElement("div");
    div.className = "input-group";
    div.id = "manualLottoDiv" + i;
    return div;
}

function makeText(i) {
    var div = document.createElement("div");
    div.className = "input-group-prepend";
    div.innerHTML = '<span class="input-group-text">로또' + (i + 1) + '</span>';
    return div;
}

window.onbeforeunload = function (e) {
    $('#manualCount')[0].value = "";
    $('#price')[0].value = "";
};

function cancel() {
    document.getElementById('winningLotto').value = document.getElementById('bonusBall').value = '';
}