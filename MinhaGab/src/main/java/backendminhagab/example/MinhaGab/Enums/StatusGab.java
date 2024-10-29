package backendminhagab.example.MinhaGab.Enums;

public enum StatusGab {
    PENDENTE("pendente"),
    EMITIDA("emitida"),
    NEGADA("negada");

    private String statusGab;

    StatusGab(String statusGab){
        this.statusGab = statusGab;
    }

    public String getStatusGab(){
        return statusGab;
    }
}