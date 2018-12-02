
// Pegar os marcadores pelo ID
var tempEntryMarcador = document.getElementById("tempEntryMarcador");
var buttonAddMarcador = document.getElementById("addmarcador");
var allMarcadores = document.getElementById("allMarcadores");



buttonAddMarcador.addEventListener("click", function () {
    // bloqueia o botão adicionar marcador
    buttonAddMarcador.setAttribute('disabled', 'true');
    // cria elemento input
    var marcador = document.createElement('input');
    marcador.setAttribute('type', 'text');
    marcador.setAttribute('placeholder', 'Digite um titulo');
    marcador.classList.add("form-control");
    tempEntryMarcador.appendChild(marcador);

    var buttonSubmitNewMarcador = document.createElement('button');
    buttonSubmitNewMarcador.setAttribute('type', 'button');



    buttonSubmitNewMarcador.classList.add("btn", "btn-secondary", "mx-auto", "ml-1");
    buttonSubmitNewMarcador.appendChild(document.createTextNode("Adicionar"));
    tempEntryMarcador.appendChild(buttonSubmitNewMarcador);
    buttonSubmitNewMarcador.addEventListener("click", function () {


        // pega as informações do input
        var tempInput = document.querySelector("#tempEntryMarcador input");


        // cria uma div para o checkbox
        var div = document.createElement('div');
        div.classList.add("custom-control", "custom-checkbox");

        // cria o checkbox
        var newMarcador = document.createElement('input');
        newMarcador.setAttribute('type', 'checkbox');
        newMarcador.setAttribute('name', 'newMarcador');
        newMarcador.classList.add("custom-control-input");
        newMarcador.setAttribute("id", tempInput.value);
        newMarcador.value = tempInput.value;

        var labelNewMarcador = document.createElement("label");
        labelNewMarcador.classList.add("custom-control-label");
        labelNewMarcador.setAttribute("for", tempInput.value);
        labelNewMarcador.appendChild(document.createTextNode(tempInput.value));

        div.appendChild(newMarcador);
        div.appendChild(labelNewMarcador);
        div.appendChild(document.createElement("br"));

        allMarcadores.appendChild(div);
        tempEntryMarcador.innerHTML = '';
        buttonAddMarcador.removeAttribute('disabled');
    });
});