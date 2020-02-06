let tickets = [];
const movies = ["Avengers: Secret Wars", "Black Widow", "1917", "Life on the Datatorg: A Documentary"];

//Method to be called when the DOM is rendered.
const init = () => {
    createSampleTickets();
    fillMovieSelector();
};

const createSampleTickets = () => {
    if (localStorage.getItem("ticketsArray") === null) {
        tickets.push(
            {
                movie: "Avengers: Secret Wars",
                quantity: 5,
                firstName: "Fredrik",
                lastName: "Pedersen",
                phoneNumber: 87654321,
                email: "fredrik@mail.com"
            },
            {
                movie: "Black Widow",
                quantity: 2,
                firstName: "Signe",
                lastName: "Eide",
                phoneNumber: 12345678,
                email: "signe@mail.com"
            }
        );
    } else {
        tickets = JSON.parse(localStorage.getItem("ticketsArray"));
    }
    console.log(tickets);
};

const fillMovieSelector = () => {
    const movieSelector = document.getElementById("movieSelector");

    movies.forEach(movie => {
        const option = document.createElement("option");
        option.value = movie;
        option.innerHTML = movie;
        movieSelector.appendChild(option);
    });
};

const addTicket = () => {
    const movieSelector = document.getElementById("movieSelector");

    const selectedMovie = movieSelector.options[movieSelector.selectedIndex].text;
    const quantity = document.getElementById("quantityInput").value;
    const firstName = document.getElementById("firstNameInput").value;
    const lastName = document.getElementById("lastNameInput").value;
    const phoneNumber = document.getElementById("phoneInput").value;
    const email = document.getElementById("emailInput").value;

    if (selectedMovie === "" || quantity === "" || firstName === "" || lastName === "" || phoneNumber === "" || email === "") {
        return;
    }

    tickets.push({
        movie: selectedMovie,
        quantity: quantity,
        firstName: firstName,
        lastName: lastName,
        phoneNumber: phoneNumber,
        email: email
    });

    localStorage.setItem("ticketsArray", JSON.stringify(tickets));
};

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

const clearLocalStorage = () => {
    localStorage.removeItem("ticketsArray");
};



