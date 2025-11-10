import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- Gerenciador de Tarefas ---");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Marcar Tarefa como Concluída");
            System.out.println("4. Remover Tarefas Concluídas");
            System.out.println("0. Sair (e Salvar)");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    System.out.print("Descrição: ");
                    String desc = scanner.nextLine();
                    System.out.print("Prioridade (1-5): ");
                    int pri = scanner.nextInt();
                    gerenciador.adicionarTarefa(desc, pri);
                    break;
                case 2:
                    gerenciador.listarTarefas();
                    break;
                case 3:
                    System.out.print("Parte da descrição da tarefa a concluir: ");
                    String descParcial = scanner.nextLine();
                    gerenciador.marcarConcluida(descParcial);
                    break;
                case 4:
                    gerenciador.removerConcluidas();
                    System.out.println("Tarefas concluídas foram removidas.");
                    break;
                case 0:
                    gerenciador.salvarTarefas(); // Salva ao sair!
                    System.out.println("Salvando e saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}