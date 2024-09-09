package backendminhagab.example.MinhaGab.dto;

import backendminhagab.example.MinhaGab.Enums.StatusComment;

public class ComentariosRequestDTO {
    private Integer userId;  
    private String comentario;
    private StatusComment status;

    // Getters e Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public StatusComment getStatus() {
        return status;
    }

    public void setStatus(StatusComment status) {
        this.status = status;
    }
}
