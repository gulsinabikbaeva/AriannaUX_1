/*

Esempio di gestione del evento sul campo username, poi generalizzato nella soluzione:

    var userName = document.querySelector("#username");

    userName.addEventListener("input", function(event){
        // se volessimo generalizzare, potremmo usare event.target (è una referenza all'oggetto che ha scatenato l'evento)
        if (userName.value.length == 0) {
            userName.classList.remove('is-invalid');
            userName.classList.remove('is-valid');
            return;
        }
        if((/^[a-zA-Z]+$/g).test(userName.value)){
            if (userName.classList.contains('is-invalid')) {
                userName.classList.remove('is-invalid')
            }
            userName.classList.add('is-valid');
        }else{
            if (userName.classList.contains('is-valid')) {
                userName.classList.remove('is-valid')
            }
            userName.classList.add('is-invalid');
        }
    });

*/

(function(){

    const register_form = document.querySelector("#register_form");
    const firstName = document.querySelector("#firstname");
    const lastName = document.querySelector("#lastname");
    const userName = document.querySelector("#username");
    const password = document.querySelector("#password");
    const password_confirm = document.querySelector("#password_confirm");
    const cancel_button = document.querySelector("#cancel_button");

    const keys = [
        {
            name: "firstname",
            field: firstName,
            verified: false,
            verifierFunc: function(firstname){
                return (/^[a-zA-Z]+$/g).test(firstname);
            }
        },
        {
            name: "lastname",
            field: lastName,
            verified: false,
            verifierFunc: function(lastname){
                return (/^[a-zA-Z]+$/g).test(lastname);
            }
        },
        {
            name: "username",
            field: userName,
            verified: false,
            verifierFunc: function(username){
                return (/^[a-zA-Z0-9_]+$/g).test(username);
            }
        },
        {
            name: "password",
            field: password,
            verified: false,
            verifierFunc: function(password){
                return (/^[a-zA-Z0-9_]{8,15}$/g).test(password);
            }
        },
        {
            name: "password_confirm",
            field: password_confirm,
            verified: false,
            verifierFunc: function(password_confirm){
                return (/^[a-zA-Z0-9_]{8,15}$/g).test(password_confirm);
            }
        }
    ];

    for(let i = 0, l=keys.length; i<l; i+=1){
        (function() {
            const current = keys[i];
            current.field.addEventListener("input", eventHandler.bind(this, current));
        })();
    }

    (checkAllVerified() && checkPasswordMatch()) ? createButton() : removeButton();

    function eventHandler(currentElement, evt) {
        if (evt.target.value.length == 0) {
            currentElement.field.classList.remove('is-invalid');
            currentElement.field.classList.remove('is-valid');
            currentElement.verified = false;
            checkAllVerified();
            return;
        }

        if (currentElement.verifierFunc(evt.target.value)) {
            if (currentElement.field.classList.contains('is-invalid')) {
                currentElement.field.classList.remove('is-invalid')
            }
            currentElement.field.classList.add('is-valid');
            currentElement.verified = true;

        } else {
            if (currentElement.field.classList.contains('is-valid')) {
                currentElement.field.classList.remove('is-valid')
            }
            currentElement.field.classList.add('is-invalid');
            currentElement.verified = false;
        }
        (checkAllVerified() && checkPasswordMatch()) ? createButton() : removeButton();
    }

    function checkAllVerified(){
        const filtered = keys.filter(function(elem){
            return elem.verified == true;
        });

        if(filtered.length==keys.length) {
            return true;
        }
        return false;
    }

    function checkPasswordMatch(){
        return password.value === password_confirm.value;
    }

    function createButton() {
        const reg = document.querySelector("#register_submit");
        //check if already present
        if (reg) {
            return;
        }

        var button = document.createElement("input");
        button.id = "register_submit";
        button.type = "submit";
        button.value = "Registrati";
        button.classList.add("btn", "btn-primary", "offset-sm-2");
        register_form.insertBefore(button, cancel_button);
        cancel_button.classList.remove("offset-sm-2");
    }

    function removeButton(){
        const reg = document.querySelector("#register_submit");
        if(reg){
            register_form.removeChild(reg);
            cancel_button.classList.add("offset-sm-2");
        }
    }

})();