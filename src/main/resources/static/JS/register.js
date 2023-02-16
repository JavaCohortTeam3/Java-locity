"use strict"

let boolUsernameHasLength = false;
let boolEmailHasLength = false;
let boolPasswordHasLength = false;
let boolConfirmPasswordHasLength = false;
let boolPasswordsMatch = false;

let boolPasswordLengthEightOrGreater;
let boolPasswordHasUppercase;
let boolPasswordHasLowercase;
let boolPasswordHasNumber;
let boolPasswordHasSpecialCharacter;

let boolPasswordIsValid;

function checkIfPasswordIsValid(){
    checkPasswordLength();
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
if (document.getElementById("password").value.length >= 8){
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
    if (containsUppercaseChars(document.getElementById("password").value)){
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
    if (containsLowercaseChars(document.getElementById("password").value)){
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
    if (containsNumbers(document.getElementById("password").value)){
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
    if (containsSpecialChars(document.getElementById("password").value)){
        document.getElementById("passwordHasSpecialCharacter").style.visibility = "hidden";
        boolPasswordHasSpecialCharacter = true;
    } else {
        document.getElementById("passwordHasSpecialCharacter").style.visibility = "visible";
        boolPasswordHasSpecialCharacter = false;
    }
}

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

document.getElementById("password").addEventListener("keyup", checkIfPasswordIsValid);

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

let SubmitButton = document.getElementById('SubmitButton');
SubmitButton.setAttribute('disabled', true);
SubmitButton.style.opacity = '.2';

function checkIfValidRegister(){
    if (boolUsernameHasLength && boolEmailHasLength && boolPasswordHasLength && boolConfirmPasswordHasLength && boolPasswordsMatch && boolPasswordIsValid){
        SubmitButton.removeAttribute('disabled')
        SubmitButton.style.opacity = '1';
    } else {
        SubmitButton.setAttribute('disabled', true)
        SubmitButton.style.opacity = '.2';
    }
}

checkUsernameLength();
checkEmailLength();
checkPasswordLength();
checkConfirmPasswordLength();
checkIfPasswordIsValid();