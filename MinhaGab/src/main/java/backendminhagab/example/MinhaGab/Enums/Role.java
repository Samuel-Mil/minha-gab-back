package backendminhagab.example.MinhaGab.Enums;

public enum Role {
    PACIENTE("paciente"),
    CLINICA("clinica"),
    FINANCEIRO("financeiro");

    private String role;

    Role(String role){
        this.role=role;
    }

    public String getRole(){
        return role;
    }
}