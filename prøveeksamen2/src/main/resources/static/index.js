$(() => {
   hentAlleBrukernavn();
});

const registrer = () => {

    const brukernavn = $("#brukernavn").val();
    const passord = $("#passord").val();

    if (validerBrukernavn(brukernavn) && validerPassord(passord)) {
        const bruker = {
            brukernavn: brukernavn,
            passord: passord
        };

        $.post("/registrer", bruker);
    }
};

const logginn = () => {
    const bruker ={
        brukernavn: $("#brukernavn").val(),
        passord: $("#passord").val()
    };

    $.get("/logginn", bruker, (valid) => {
        if (valid === true) {
            $("#melding").html("Logget inn!")
        } else {
            $("#melding").html("Feil påloggingsinformasjon!")
        }
    })
};

const hentAlleBrukernavn = () => {
    $.get("/hentAlleBrukernavn", (data) => {
        if (data) {
            printBrukere(data);
        } else {
            $("#brukere").html("Logg inn for å hente brukerinformasjon!").css({color: "red"});
        }
    })
};

const printBrukere = (brukere) => {
    let ut = "<table class='table table-striped'><tr><th>Brukernavn</th></tr>";

    brukere.forEach(bruker => {
        ut += "<tr><td>" + bruker + "</td></tr>";
    });

    ut += "</table>";
    $("#brukere").html(ut);
};

const validerBrukernavn = (brukernavn) => {
    const regexp = /^[a-zæøåA-ZÆØÅ0-9 .\-]{2,30}$/;
    const gyldig = regexp.test(brukernavn);

    if (!gyldig) {
        $("#feilBrukernavn").html("Brukernavnet må være mellom 2og 30 bokstaver og tall(bindestrek og mellomrom er også tillatt)");
        return false;
    } else {
        $("#feilBrukernavn").html("");
        return true;
    }
};

const validerPassord = (passord) => {
    const regexp = /^[a-zøæåA-ZÆØÅ0-9]{6,10}$/;
    const gyldig = regexp.test(passord);

    if (!gyldig) {
        $("#feilPassord").html("Passordet må være mellom 6 og 10 bokstaver og tall");
        return false;
    } else {
        $("#feilPassord").html("");
        return true;
    }
};