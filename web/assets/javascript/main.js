/* 
 * Copyright (C) 2018 campo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */







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