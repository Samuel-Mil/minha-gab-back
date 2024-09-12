package backendminhagab.example.MinhaGab.dto;

public class AnswerRequestDTO {

    private Long commentId;
    private String answer;

    public Long getCommentId() {
        return commentId;
    }
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
