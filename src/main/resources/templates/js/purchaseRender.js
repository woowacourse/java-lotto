(function(){
    manualInputGroupRender();
})();

function manualInputGroupRender() {
    var manualCount = document.querySelector('#manualCount').getAttribute('value');

    for (var i = 0; i < Number(manualCount); i++) {
        document.querySelector('#manualLottoGroup').prepend(createLottoInput());
    }
}

function createLottoInput() {
    var div = document.createElement('DIV');
    var input = document.createElement('INPUT');
    input.type = 'text';
    input.name = 'manualLotto';
    div.append(input);

    return div;
}