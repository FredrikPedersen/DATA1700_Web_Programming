package com.fredrikpedersen.proveeksamen2.web;

import com.fredrikpedersen.proveeksamen2.domain.Bruker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 03/06/2020 at 12:25
 */

@Slf4j
@RestController
public class BrukerController {

    private final JdbcTemplate database;
    private final HttpSession httpSession;

    public BrukerController(final JdbcTemplate database, final HttpSession httpSession) {
        this.database = database;
        this.httpSession = httpSession;
    }

    @PostMapping("/registrer")
    public void registerBruker(final Bruker bruker) {

        if (validerBrukernavn(bruker.getBrukernavn()) && validerPassord(bruker.getPassord())) {
            try {
                final String SQL = "INSERT INTO Bruker(brukernavn, passord)" + "VALUES(?,?)";
                database.update(SQL, bruker.getBrukernavn(), bruker.getPassord());
            } catch (Exception e) {
                log.error("Databasefeil ved lagring av bruker");
            }
        } else {
            log.error("Ugyldig brukernavn eller passord!");
        }
    }

    @GetMapping("/hentAlleBrukernavn")
    public List<String> hentAlleBrukernavn() {
        if (httpSession.getAttribute("innlogget") != null) {

            try {
                final String SQL = "SELECT brukernavn FROM Bruker";
                return database.queryForList(SQL, String.class);
            } catch (Exception e) {
                log.error("Feil ved henting av brukernavn fra Database");
                return null;
            }

        }

        return null;
    }

    @GetMapping("/logginn")
    public boolean logginn(final Bruker bruker) {

        final String SQL = "SELECT count(*) FROM Bruker WHERE passord = ?";
        final int passord = database.queryForObject(SQL, Integer.class, bruker.getPassord());

        if (passord > 0) {
            httpSession.setAttribute("innlogget", bruker);
            return true;
        }

        return false;
    }

    private boolean validerBrukernavn(final String brukernavn) {
        final String regex = "[a-zøåA-ZÆØÅ0-9 .\\-]{2,30}";
        return brukernavn.matches(regex);
    }

    private boolean validerPassord(final String passord) {
        final String regex = "[a-øæååA-ZÆØÅ0-9]{6,10}";
        return passord.matches(regex);
    }
}
