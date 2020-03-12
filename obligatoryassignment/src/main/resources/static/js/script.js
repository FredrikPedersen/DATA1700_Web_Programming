let tickets = [];

//Boolean to keep track of whether the user has opted to delete all tickets. If they have, not sampleTickets should be generated.
let createSampleData = window.localStorage.getItem("createDataBool") === null ? true : JSON.parse(window.localStorage.getItem("createDataBool"));
const movies = ["Avengers: Secret Wars", "Black Widow", "1917", "Life on the Datatorg: A Documentary"];

//Method to be called when the DOM is rendered.
const init = () => {
    createSampleTickets();
    fillMovieSelector();
    listTickets();
};

//If there are no tickets in localStorage and the user haven't deleted all tickets, generate some sample tickets.
const createSampleTickets = () => {
    if (window.localStorage.getItem("ticketsArray") === null && createSampleData) {
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
        tickets = window.localStorage.getItem("ticketsArray") === null ? [] : JSON.parse(window.localStorage.getItem("ticketsArray"));
    }
};

//Dynamically fills the HTML Selector for movies with values from the movies array
const fillMovieSelector = () => {
    const movieSelector = $("#movieSelector")[0];

    movies.forEach(movie => {
        const option = document.createElement("option");
        option.value = movie;
        option.innerHTML = movie;
        movieSelector.appendChild(option);
    });
};


const addTicket = () => {

    //Fetch all values from the HTML form
    const movieSelector = $("#movieSelector")[0];

    const selectedMovie = movieSelector.options[movieSelector.selectedIndex].text;
    const quantity = $("#quantityInput").val();
    const firstName = $("#firstNameInput").val();
    const lastName = $("#lastNameInput").val();
    const phoneNumber = $("#phoneInput").val();
    const email = $("#emailInput").val();

    //Simple input-validation to make sure we have no empty fields.
    if (selectedMovie === "" || quantity === "" || firstName === "" || lastName === "" || phoneNumber === "" || email === "") return;

    tickets.push({
        movie: selectedMovie,
        quantity: quantity,
        firstName: firstName,
        lastName: lastName,
        phoneNumber: phoneNumber,
        email: email
    });

    window.localStorage.setItem("ticketsArray", JSON.stringify(tickets));
    window.localStorage.setItem("createDataBool", "true");
};

//Dynamically creates table columns based on number of tickets and fills them with values.
const listTickets = () => {
    const tableBody = $('#tableBody')[0];
    tickets.sort((a, b) => sortList(a,b));

    tickets.forEach(ticket => {
        const tableRow = document.createElement("tr");

        const tableColumnMovie = document.createElement("th");
        tableColumnMovie.textContent = ticket.movie;

        const tableColumnQuantity = document.createElement("th");
        tableColumnQuantity.textContent = ticket.quantity;

        const tableColumnName = document.createElement("th");
        tableColumnName.textContent = ticket.firstName + " " + ticket.lastName;

        const tableColumnPhone = document.createElement("th");
        tableColumnPhone.textContent = ticket.phoneNumber;

        const tableColumnMail = document.createElement("th");
        tableColumnMail.textContent = ticket.email;

        tableRow.appendChild(tableColumnMovie);
        tableRow.appendChild(tableColumnQuantity);
        tableRow.appendChild(tableColumnName);
        tableRow.appendChild(tableColumnPhone);
        tableRow.appendChild(tableColumnMail);
        tableBody.appendChild(tableRow);
    })
};

//Simple fort function, prioritizing firstName, then lastName.
const sortList = (ticketA, ticketB) => {
    if (ticketA.firstName === ticketB.firstName) {
        return ticketA.lastName > ticketB.lastName ? 1 : -1;
    } else {
        return ticketA.firstName > ticketB.firstName ? 1 : -1;
    }
};

//Code taken from Bootstrap's documentation on how to do form validation, with some modifications to remove redundancies.
//See https://getbootstrap.com/docs/4.3/components/forms/#validation
( () => {
    window.addEventListener('load',  () => {
        const forms = document.getElementsByClassName('needs-validation');

        Array.prototype.filter.call(forms,  form => {
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

//Removes the ticketArray from localStorage, disables generation of sample values and removes the HTML table columns.
const deleteTickets = () => {
    tickets = [];
    window.localStorage.removeItem("ticketsArray");
    window.localStorage.setItem("createDataBool", "false");

    const tableBody = $('#tableBody')[0];
    while (tableBody.firstChild) {
        tableBody.removeChild(tableBody.firstChild);
    }
};

//Function used for resetting localStorage.
//Reverts local storage to the state it was before you did anything, handy for testing.
const clearLocalStorage = () => {
    window.localStorage.removeItem("ticketsArray");
    window.localStorage.removeItem("createDataBool");
};