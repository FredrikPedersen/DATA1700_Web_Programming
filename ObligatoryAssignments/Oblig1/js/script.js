//Code mostly taken from Bootstrap's documentation on how to do form validation.
( () => {
    window.addEventListener('load',  () => {
        const forms = document.getElementsByClassName('needs-validation');

        const validation = Array.prototype.filter.call(forms,  form => {
            form.addEventListener('submit',  event => {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }

                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();