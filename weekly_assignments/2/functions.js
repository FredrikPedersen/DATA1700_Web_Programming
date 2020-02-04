const persons = [];

const init = () => {
    persons.push(
        {
            name: "Fredrik",
            address: "Sagveien 9",
            phone: "99504112",
            nin: "02119435777"
        },
        {
            name: "Signe",
            address: "Sagveien 9",
            phone: "94899751",
            nin: "12029732632"
        }
    );

    printPersons();
};

const addPerson = () => {
    const nameInput = document.getElementById("nameInput").value;
    const addressInput = document.getElementById("addressInput").value;
    const phoneInput = document.getElementById("phoneInput").value;
    const ninInput = document.getElementById("ninInput").value;

    if (nameInput === "" || addressInput === "" || phoneInput === "" || ninInput === "") {
        console.log("undefined");
        return;
    }

    const person = {
        name: nameInput,
        address: addressInput,
        phone: phoneInput,
        nin: ninInput
    };

    persons.push(person);
    printPersons();
    clearInputs();
};

const printPersons = () => {
    const tableBody = document.getElementById('tableBody');

    while (tableBody.firstChild) {
        tableBody.removeChild(tableBody.firstChild);
    }

    persons.sort((a, b) => sortList(a, b));

    for (let i = 0; i < persons.length; i++) {
        let liStyle = "padding-left:1.5rem;";

        if (i % 2 === 0) {
            liStyle += "font-weight:normal;"
        }

        if (isFemale(persons[i].nin)) {
            liStyle += "color:red;"
        }

        const tableRow = document.createElement('tr');

        const tableColumnName = document.createElement('th');
        tableColumnName.style.cssText = liStyle;
        tableColumnName.textContent = persons[i].name;

        const tableColumnAddress = document.createElement('th');
        tableColumnAddress.style.cssText = liStyle;
        tableColumnAddress.textContent = persons[i].address;

        const tableColumnPhone = document.createElement('th');
        tableColumnPhone.style.cssText = liStyle;
        tableColumnPhone.textContent = persons[i].phone;

        const tableColumnNIN = document.createElement('th');
        tableColumnNIN.style.cssText = liStyle;
        tableColumnNIN.textContent = persons[i].nin;

        tableRow.appendChild(tableColumnName);
        tableRow.appendChild(tableColumnAddress);
        tableRow.appendChild(tableColumnPhone);
        tableRow.appendChild(tableColumnNIN);
        tableBody.appendChild(tableRow);
    }
};

const clearInputs = () => {
    document.getElementById('nameInput').value = '';
    document.getElementById('addressInput').value = '';
    document.getElementById('phoneInput').value = '';
    document.getElementById('ninInput').value = '';
};

const sortList = (personA, personB) => {
    if (personA.name === personB.name) {
        return personA.address > personB.address ? 1 : -1;
    } else {
        return personA.name > personB.name ? 1 : -1;
    }
};

const isFemale = (nin) => {
    const split = nin.split("");
    return split[8] % 2 === 0;
};