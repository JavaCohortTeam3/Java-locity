"use strict"

let boolCurrentPasswordHasLength = false;
let boolNewPasswordHasLength = false;
let boolConfirmPasswordHasLength = false;
let boolPasswordsMatch = false;

document.getElementById("currentPassword").addEventListener("keyup", checkCurrentPasswordLength);

function checkCurrentPasswordLength(){
    if (document.getElementById("currentPassword").value.length == 0){
        document.getElementById("passwordIsEmpty").style.visibility = "visible";
        boolCurrentPasswordHasLength = false;
    } else {
        document.getElementById("passwordIsEmpty").style.visibility = "hidden";
        boolCurrentPasswordHasLength = true;
    }
}

document.getElementById("newPassword").addEventListener("keyup", checkNewPasswordLength);

function checkNewPasswordLength(){
    checkIfPasswordsMatch();
    if (document.getElementById("newPassword").value.length == 0){
        document.getElementById("newPasswordIsEmpty").style.visibility = "visible";
        boolNewPasswordHasLength = false;
    } else {
        document.getElementById("newPasswordIsEmpty").style.visibility = "hidden";
        boolNewPasswordHasLength = true;
    }
}

document.getElementById("confirmNewPassword").addEventListener("keyup", checkConfirmPasswordLength);

function checkConfirmPasswordLength(){
    checkIfPasswordsMatch();
    if (document.getElementById("confirmNewPassword").value.length == 0){
        document.getElementById("confirmNewPasswordIsEmpty").style.visibility = "visible";
        boolConfirmPasswordHasLength = false;
    } else {
        document.getElementById("confirmNewPasswordIsEmpty").style.visibility = "hidden";
        boolConfirmPasswordHasLength = true;
    }
}

function checkIfPasswordsMatch(){
    if (document.getElementById("newPassword").value != document.getElementById("confirmNewPassword").value){
        document.getElementById("passwordsDoNotMatch").innerText = "Passwords Do Not Match";
        boolPasswordsMatch = false;
    } else {
        document.getElementById("passwordsDoNotMatch").innerText = "Passwords Match!";
        boolPasswordsMatch = true;
        checkIfValidRegister();
    }
}

let SubmitButton = document.getElementById('SubmitButton');
SubmitButton.setAttribute('disabled', true);
SubmitButton.style.opacity = '.2';

function checkIfValidRegister(){
    if (boolCurrentPasswordHasLength && boolNewPasswordHasLength && boolConfirmPasswordHasLength && boolPasswordsMatch){
        SubmitButton.removeAttribute('disabled')
        SubmitButton.style.opacity = '1';
    } else {
        SubmitButton.setAttribute('disabled', true)
        SubmitButton.style.opacity = '.2';
    }
}
checkCurrentPasswordLength();
checkNewPasswordLength();
checkConfirmPasswordLength();
checkIfValidRegister();

