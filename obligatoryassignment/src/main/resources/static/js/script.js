const movies = ["Avengers: Secret Wars", "Black Widow", "1917", "Life on the Datatorg: A Documentary"];
const apiUrl = "http://localhost:8080/api/v1/tickets/";

/**
 * Adds an event listener to the window when the DOM is finished rendering.
 * Calls the init method to initialize the view, then adds an event listener on the ticketForm
 * to override it's default submit behaviour to do input validation and then do a POST request
 * to our API if input was validated.
 */
window.addEventListener("load", () => {
    init();
    const form = $("#ticketForm")[0];

    form.addEventListener('submit', event => {
        event.preventDefault();
        form.classList.remove('was-validated');

        if (form.checkValidity() === false) {
            event.stopPropagation();
            form.classList.add('was-validated');
        } else {
            addTicket();
            form.reset();
        }
    }, false);

}, false);

//Method to be called when the DOM is rendered.
const init = () => {
    fillMovieSelector();
    listTickets();
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

    const ticketDTO = {
        movie: selectedMovie,
        quantity: quantity,
        firstName: firstName,
        lastName: lastName,
        phoneNumber: phoneNumber,
        email: email
    };

    axios.post(apiUrl, ticketDTO)
        .then(response => {
            console.log(response);
            listTickets();
        })
        .catch(error => console.log(error));
};

//Dynamically creates table columns based on number of tickets and fills them with values.
const listTickets = async () => {
    clearTicketTable();
    const tableBody = $('#tableBody')[0];
    const tickets = await getTickets();

    tickets.sort((a, b) => sortList(a, b));

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

const getTickets = async () => {
    const response = await axios.get(apiUrl);
    return response.data.tickets;
};

//Simple fort function, prioritizing firstName, then lastName.
const sortList = (ticketA, ticketB) => {
    if (ticketA.firstName === ticketB.firstName) {
        return ticketA.lastName > ticketB.lastName ? 1 : -1;
    } else {
        return ticketA.firstName > ticketB.firstName ? 1 : -1;
    }
};


const deleteTickets = () => {

    swal({
        title: "Are you sure?",
        text: "Once deleted, all tickets will be lost forever!",
        icon: "warning",
        buttons: true
    })
        .then((confirmation) => {
            if (confirmation) {
                performDeletion();
                swal("Tickets deleted!", {
                    icon: "success"
                })
            }
        });
};

const performDeletion = () => {
    axios.delete(apiUrl);
    clearTicketTable();
};

const clearTicketTable = () => {
    const tableBody = $('#tableBody')[0];
    while (tableBody.firstChild) {
        tableBody.removeChild(tableBody.firstChild);
    }
};