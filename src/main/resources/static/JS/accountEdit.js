"use strict"

let boolPasswordHasLength = false;

let SubmitButton = document.getElementById('SubmitButton');
SubmitButton.setAttribute('disabled', true);
SubmitButton.style.opacity = '.2';

document.getElementById("password").addEventListener("keyup", checkPasswordLength);

function checkPasswordLength(){
    if (document.getElementById("password").value.length == 0){
        document.getElementById("passwordIsEmpty").style.visibility = "visible";
        boolPasswordHasLength = false;
    } else {
        document.getElementById("passwordIsEmpty").style.visibility = "hidden";
        boolPasswordHasLength = true;
    }
    checkIfValidRegister();
}

function checkIfValidRegister(){
    if (boolPasswordHasLength){
        SubmitButton.removeAttribute('disabled')
        SubmitButton.style.opacity = '1';
    } else {
        SubmitButton.setAttribute('disabled', true)
        SubmitButton.style.opacity = '.2';
    }
}

checkPasswordLength();