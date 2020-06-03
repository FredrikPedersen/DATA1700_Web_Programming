package oslomet.webprog;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Motorvogn {
    private int id;
    private String personnr;
    private String navn;
    private String adresse;
    private String kjennetegn;
    private String merke;
    private String type;

}
