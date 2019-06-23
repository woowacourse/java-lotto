var countOfManualLotto = new URLSearchParams(window.location.search).get("countOfManualLotto");

for (var i = 0; i < countOfManualLotto; i++) {
    var inputForm = "<br>수동 Lotto "+(i+1)+"<input type='text'>";
    document.getElementById("inputManualLotto").innerHTML += inputForm;
}
