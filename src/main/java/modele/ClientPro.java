package modele;

public class ClientPro extends ClientPart {
    private final String siret;

    public ClientPro(String email, String nom, String prenom, String numeroTel, String password, String siret) {
        super(email, nom, prenom, numeroTel, password);
        this.siret = siret;
    }

    public String getSiret() {
        return siret;
    }

}
