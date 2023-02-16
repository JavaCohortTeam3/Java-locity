"use strict"

let boolCurrentPasswordHasLength = false;
let boolNewPasswordHasLength = false;
let boolConfirmPasswordHasLength = false;
let boolPasswordsMatch = false;


let boolPasswordLengthEightOrGreater;
let boolPasswordHasUppercase;
let boolPasswordHasLowercase;
let boolPasswordHasNumber;
let boolPasswordHasSpecialCharacter;

let boolPasswordIsValid;

function checkIfPasswordIsValid(){
    checkNewPasswordLength();
    checkPasswordLengthEightOrGreater();
    checkPasswordHasUppercase();
    checkPasswordHasLowercase();
    checkPasswordHasNumber();
    checkPasswordHasSpecialCharacter();
    if (boolPasswordLengthEightOrGreater && boolPasswordHasUppercase && boolPasswordHasLowercase && boolPasswordHasNumber && boolPasswordHasSpecialCharacter){
        boolPasswordIsValid = true;
    } else {
        boolPasswordIsValid = false;
    }
    checkIfValidRegister();
}

function checkPasswordLengthEightOrGreater(){
    if (document.getElementById("newPassword").value.length >= 8){
        document.getElementById("passwordLengthEightOrGreater").style.visibility = "hidden";
        boolPasswordLengthEightOrGreater = true;
    } else {
        document.getElementById("passwordLengthEightOrGreater").style.visibility = "visible";
        boolPasswordLengthEightOrGreater = false;
    }
}

function containsUppercaseChars(str) {
    return /[A-Z]/.test(str);
}

function checkPasswordHasUppercase(){
    if (containsUppercaseChars(document.getElementById("newPassword").value)){
        document.getElementById("passwordHasUppercase").style.visibility = "hidden";
        boolPasswordHasUppercase = true;
    } else {
        document.getElementById("passwordHasUppercase").style.visibility = "visible";
        boolPasswordHasUppercase = false;
    }
}

function containsLowercaseChars(str) {
    return /[a-z]/.test(str);
}

function checkPasswordHasLowercase(){
    if (containsLowercaseChars(document.getElementById("newPassword").value)){
        document.getElementById("passwordHasLowercase").style.visibility = "hidden";
        boolPasswordHasLowercase = true;
    } else {
        document.getElementById("passwordHasLowercase").style.visibility = "visible";
        boolPasswordHasLowercase = false;
    }
}

function containsNumbers(str) {
    return /[0-9]/.test(str);
}

function checkPasswordHasNumber(){
    if (containsNumbers(document.getElementById("newPassword").value)){
        document.getElementById("passwordHasNumber").style.visibility = "hidden";
        boolPasswordHasNumber = true;
    } else {
        document.getElementById("passwordHasNumber").style.visibility = "visible";
        boolPasswordHasNumber = false;
    }
}


function containsSpecialChars(str) {
    const specialChars =
        /[`!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
    return specialChars.test(str);
}

function checkPasswordHasSpecialCharacter(){
    if (containsSpecialChars(document.getElementById("newPassword").value)){
        document.getElementById("passwordHasSpecialCharacter").style.visibility = "hidden";
        boolPasswordHasSpecialCharacter = true;
    } else {
        document.getElementById("passwordHasSpecialCharacter").style.visibility = "visible";
        boolPasswordHasSpecialCharacter = false;
    }
}

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

document.getElementById("newPassword").addEventListener("keyup", checkIfPasswordIsValid);

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
    if (boolCurrentPasswordHasLength && boolNewPasswordHasLength && boolConfirmPasswordHasLength && boolPasswordsMatch && boolPasswordIsValid){
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
checkIfPasswordIsValid();
