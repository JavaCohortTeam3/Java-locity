"use strict"

let boolUsernameHasLength = false;
let boolEmailHasLength = false;
let boolPasswordHasLength = false;
let boolConfirmPasswordHasLength = false;
let boolPasswordsMatch = false;

document.getElementById("username").addEventListener("keyup", checkUsernameLength);

function checkUsernameLength(){
    if (document.getElementById("username").value.length == 0){
        document.getElementById("usernameIsEmpty").style.visibility = "visible";
        boolUsernameHasLength = false;
    } else {
        document.getElementById("usernameIsEmpty").style.visibility = "hidden";
        boolUsernameHasLength = true;
    }
}

document.getElementById("email").addEventListener("keyup", checkEmailLength);

function checkEmailLength(){
    if (document.getElementById("email").value.length == 0){
        document.getElementById("emailIsEmpty").style.visibility = "visible";
        boolEmailHasLength = false;
    } else {
        document.getElementById("emailIsEmpty").style.visibility = "hidden";
        boolEmailHasLength = true;
    }
}

document.getElementById("password").addEventListener("keyup", checkPasswordLength);

function checkPasswordLength(){
    checkIfPasswordsMatch();
    if (document.getElementById("password").value.length == 0){
        document.getElementById("passwordIsEmpty").style.visibility = "visible";
        boolPasswordHasLength = false;
    } else {
        document.getElementById("passwordIsEmpty").style.visibility = "hidden";
        boolPasswordHasLength = true;
    }
}

document.getElementById("confirmPassword").addEventListener("keyup", checkConfirmPasswordLength);

function checkConfirmPasswordLength(){
    checkIfPasswordsMatch();
    if (document.getElementById("confirmPassword").value.length == 0){
        document.getElementById("confirmPasswordIsEmpty").style.visibility = "visible";
        boolConfirmPasswordHasLength = false;
    } else {
        document.getElementById("confirmPasswordIsEmpty").style.visibility = "hidden";
        boolConfirmPasswordHasLength = true;
    }
}

function checkIfPasswordsMatch(){
    if (document.getElementById("password").value != document.getElementById("confirmPassword").value){
        document.getElementById("passwordsDoNotMatch").innerText = "Passwords Do Not Match";
        boolPasswordsMatch = false;
    } else {
        document.getElementById("passwordsDoNotMatch").innerText = "Passwords Match!";
        boolPasswordsMatch = true;
        checkIfValidRegister();
    }
}

let registerSubmitButton = document.getElementById('registerSubmit');
registerSubmitButton.setAttribute('disabled', true);
registerSubmitButton.style.opacity = '.2';

function checkIfValidRegister(){
    if (boolUsernameHasLength && boolEmailHasLength && boolPasswordHasLength && boolConfirmPasswordHasLength && boolPasswordsMatch){
        registerSubmitButton.removeAttribute('disabled')
        registerSubmitButton.style.opacity = '1';
    } else {
        registerSubmitButton.setAttribute('disabled', true)
        registerSubmitButton.style.opacity = '.2';
    }
}

checkUsernameLength();
checkEmailLength();
checkPasswordLength();
checkConfirmPasswordLength();