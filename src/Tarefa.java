import java.io.Serializable;
public class Tarefa implements Serializable{
    String descricao;
    boolean concluida;
    int prioridade;

    public Tarefa(String descricao, int prioridade){
        this.descricao = descricao;
        this.concluida = false;
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        String status = (concluida) ? "[X]" : "[]";
        return status + " " + descricao + "(Prioridade: " + prioridade + ")";
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
}
