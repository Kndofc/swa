import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
public class Tarefa {
    private String descricao;
    private LocalDate dataConclusao;

    // Construtor
    public Tarefa(String descricao, LocalDate dataConclusao) {
        this.descricao = descricao;
        this.dataConclusao = dataConclusao;
    }

    // Getters e Setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    // toString para exibição na lista
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return descricao + " - " + dataConclusao.format(formatter);
    }
}
