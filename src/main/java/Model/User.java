package Model;

public class User {
    private long id_Utente;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private boolean permission;

    public void setId_Utente(long id_Utente) {
        this.id_Utente = id_Utente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public long getId_Utente() {
        return id_Utente;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean getPermission() {
        return permission;
    }
}
