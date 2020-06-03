package oslomet.webprog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bruker {
    private int id;
    private String brukernavn;
    private String passord;

}

