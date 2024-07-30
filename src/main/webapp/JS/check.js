document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("form").addEventListener("submit", function(event) {
        event.preventDefault();

        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        const emailError = document.getElementById("emailError");
        const passwordError = document.getElementById("passwordError");

        emailError.textContent = "";
        passwordError.textContent = "";
        emailError.classList.remove("active");
        passwordError.classList.remove("active");

        let isValid = true;

        if (!validateEmail(email)) {
            emailError.textContent = "Inserisci un'email valida.";
            emailError.classList.add("active");
            isValid = false;
        }

        if (!validatePassword(password)) {
            passwordError.textContent = "La password deve essere di almeno 8 caratteri e contenere almeno una lettera maiuscola, una minuscola, un numero e un carattere speciale.";
            passwordError.classList.add("active");
            isValid = false;
        }

        if (isValid) {
            event.target.submit();
        }
    });

    function validateEmail(email) {
        const re = /^[\w\-\.]+@([\w-]+\.)+[\w-]{2,}$/;
        return re.test(email);
    }

    function validatePassword(password) {
        const pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\[\]{};':"\\|,.<>\/?]).{8,}$/;
        return pattern.test(password);
    }
});
