//Global variables
const persons = [];
let legalOperation = true; //The calculator is only allowed to have one operand at the same time.

const init = () => {
    hideErrorMessages();
};

const addPerson = () => {
    hideErrorMessages();
    const nameInput = document.getElementById("nameInput").value;
    const ageInput = document.getElementById("ageInput").value;

    if (nameInput === "" || !ageIsValid(ageInput)) {
        showErrorMessages();
        return;
    }

    const person = {
        name: nameInput,
        age: ageInput,
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

        const tableRow = document.createElement("tr");

        const tableColumnName = document.createElement("td");
        tableColumnName.textContent = persons[i].name;

        const tableColumnAge = document.createElement("td");
        tableColumnAge.textContent = persons[i].age;

        tableRow.appendChild(tableColumnName);
        tableRow.appendChild(tableColumnAge);
        tableBody.appendChild(tableRow);
    }
};

const clearInputs = () => {
    document.getElementById("nameInput").value = "";
    document.getElementById("ageInput").value = "";
};

const sortList = (personA, personB) => {
    if (personA.name === personB.name) {
        return personA.address > personB.address ? 1 : -1;
    } else {
        return personA.name > personB.name ? 1 : -1;
    }
};

const ageIsValid = (ageInput) => {
    return !(ageInput <= 0 || ageInput === "");
};

const showErrorMessages = () => {
    document.getElementById("ageErrorField").style.visibility = "visible";
    document.getElementById("ageInput").className = "form-control inputErrorState";
};

const hideErrorMessages = () => {
    document.getElementById("ageErrorField").style.visibility = "hidden";
    document.getElementById("ageInput").className = "form-control";
};

const calculateCelsius = (fahrenheit) => {
    document.getElementById("celsiusInput").value = ((5 / 9) * (fahrenheit - 32)).toString();
};

const calculateFahrenheit = (celsius) => {
    document.getElementById("fahrenheitInput").value = ((9 / 5) * celsius + 32).toString();
};

const clearTemperatures = () => {
    document.getElementById("celsiusInput").value = "";
    document.getElementById("fahrenheitInput").value = ""
};

const inputValue = (buttonInput) => {
    const numberPanel = document.getElementById("numberPanel");

    if (buttonInput === "+" || buttonInput === "-" || buttonInput === "/" || buttonInput === "*") {
        if (legalOperation) {
            numberPanel.value = numberPanel.value + buttonInput;
            legalOperation = false;
        }
    } else {
        numberPanel.value = numberPanel.value + buttonInput;
    }
};

const calculateValue = () => {
    const numberPanel = document.getElementById("numberPanel");
    const allInput = numberPanel.value;
    let operator = findOperator(allInput);
    let splitInput = allInput.split(operator);

    //No operator found
    if (splitInput.length === 0) {
        return;
    }

    switch (operator) {
        case "+":
            numberPanel.value = parseInt(splitInput[0]) + parseInt(splitInput[1]);
            break;
        case "-":
            numberPanel.value = parseInt(splitInput[0]) - parseInt(splitInput[1]);
            break;
        case "*":
            numberPanel.value = parseInt(splitInput[0]) * parseInt(splitInput[1]);
            break;
        case "/":
            numberPanel.value = parseInt(splitInput[0]) / parseInt(splitInput[1]);
            break;
    }

    legalOperation = true;
};

const clearCalculator = () => {
    document.getElementById("numberPanel").value = "";
    legalOperation = true;
};

const findOperator = (allInput) => {
    let operator = "";
    [...allInput].forEach(char => {
        if (char === "+" || char === "-" || char === "*" || char === "/") {
            operator = char;
        }
    });
    return operator;
};