package org.example.view;
import org.example.model.Usuario;
import org.example.repository.UsuarioRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner type = new Scanner(System.in);
    private UsuarioRepository repository = new UsuarioRepository();

    public void iniciar (){

        if(!repository.existeUsuario()){
            System.out.println("Nenhum usuario cadastrado.");
            System.out.println("Voce precisa criar um usuario primeiro");

            Usuario Usuario;
            Usuario = new Usuario();

            System.out.println("Nome: ");
            Usuario.setNome(type.nextLine());

            Usuario.setcpf(null);

            while (Usuario.getcpf() == null) {
                System.out.println("CPF: (ATE 11 CARACTERS)");
                String entrada = type.nextLine();
                if (entrada.matches("\\d{11}")) {
                    Usuario.setcpf(entrada);
                } else {
                    System.out.println("Formato invalido");
                }
            }

            Usuario.setgmail(null);

            while (Usuario.getgmail() == null) {
                System.out.println("gmail: ");
                String entrada = type.nextLine();
                if (entrada.endsWith("@gmail.com")) {
                    Usuario.setgmail(entrada);
                } else {
                    System.out.println("Formato invalido");
                }
            }

            System.out.println("senha: ");
            Usuario.setSenha(type.nextLine());

            System.out.println("rg: ");
            Usuario.setRg(type.nextLine());

            Usuario.setData_nascimento(null);

            while (Usuario.getData_nascimento() == null) {

                try {
                    System.out.println("Data de Nascimento (AAAA-MM-DD): ");
                    String dataInput = type.nextLine();
                    Usuario.setData_nascimento(LocalDate.parse(dataInput));
                } catch (Exception e) {
                    System.out.println("Formato invalido");
                }
            }

            repository.inserir(Usuario);
            System.out.println("Usuario cadastrado com Sucesso!");
        }else{
            boolean logado = false;
            while (!logado){
                System.out.println("==== TELA DE LOGIN ====");

                System.out.println("GMAIL: ");
                String gmailLogin = type.nextLine();

                System.out.println("SENHA: ");
                String senhaLogin = type.nextLine();

                if (repository.fazerLogin(gmailLogin,senhaLogin)){
                    System.out.println("Login feito com Sucesso!");
                    logado = true;
                }else{
                    System.out.println("Gmail ou Senha incorreto");
                }
            }
        }

        int opcao = 0;
        int idUsuario = 0;
        while (opcao != 5){
            System.out.println("\n=====MENU=====");
            System.out.println("\n1 - Inserir Usuario");
            System.out.println("\n2 - Listar os Usuarios");
            System.out.println("\n3 - Deletar Usuario");
            System.out.println("\n4 - Atualizar Cadadastro");
            System.out.println("\n5 - Sair");

            opcao = type.nextInt();
            type.nextLine();

            switch (opcao) {

                case 1:

                    Usuario Usuario;
                    Usuario = new Usuario();

                    System.out.println("Nome: ");
                    Usuario.setNome(type.nextLine());

                    Usuario.setcpf(null);

                    while (Usuario.getcpf() == null) {
                        System.out.println("CPF: (ATE 11 CARACTERS)");
                        String entrada = type.nextLine();
                        if (entrada.matches("\\d{11}")) {
                            Usuario.setcpf(entrada);
                        } else {
                            System.out.println("Formato invalido");
                        }
                    }

                    Usuario.setgmail(null);

                    while (Usuario.getgmail() == null) {
                        System.out.println("gmail: ");
                        String entrada = type.nextLine();
                        if (entrada.endsWith("@gmail.com")) {
                            Usuario.setgmail(entrada);
                        } else {
                            System.out.println("Formato invalido");
                        }
                    }


                    System.out.println("senha: ");
                    Usuario.setSenha(type.nextLine());

                    System.out.println("rg: ");
                    Usuario.setRg(type.nextLine());

                    Usuario.setData_nascimento(null);

                    while (Usuario.getData_nascimento() == null) {
                        try {
                            System.out.println("Data de Nascimento (AAAA-MM-DD): ");
                            String dataInput = type.nextLine();
                            Usuario.setData_nascimento(LocalDate.parse(dataInput));
                        } catch (Exception e) {
                            System.out.println("Formato invalido");
                        }
                    }

                    repository.inserir(Usuario);
                    System.out.println("Usuario cadastrado com Sucesso!");


                    break;

                case 2:

                    int totalUsuarios = repository.contarUsuarios();
                    int limite = 1;
                    int paginaAtual = 1;

                    if (totalUsuarios == 0){
                        System.out.println("lista vazia (Não tem Usuario)");
                        return;
                    }
                    int totalPaginas = (int) Math.ceil((double) totalUsuarios / limite);

                    boolean Continue = true;

                    while (Continue){

                        System.out.println("\nPágina "+paginaAtual+" de "+ totalPaginas);

                        List<Usuario> usuarios = repository.listaTabela(paginaAtual, limite);
                            for (Usuario u : usuarios) {
                                System.out.println("USUARIOS JA CADASTRADO:");
                                System.out.println("ID: " + u.getIdUsuario() +
                                        "\nNome: " + u.getNome() +
                                        "\nCPF: " + u.getcpf() +
                                        "\nGmail: " + u.getgmail() +
                                        "\nSenha (com Hash): " + u.getSenha() +
                                        "\nRG: " + u.getRg() +
                                        "\nData_Nascimento: " + u.getData_nascimento() +
                                        "\nData_Cadastro: " + u.getData_cadastro());
                            }
                        System.out.println("\n1 - Proxima pagina");
                        System.out.println("\n2 - Página anterior");
                        System.out.println("\n3 - Sair");
                        int opcao2 = type.nextInt();
                        type.nextLine();
                        switch (opcao2){
                            case 1:
                                if(paginaAtual >= totalPaginas){
                                    System.out.println("Esta é a ultima pagina");
                                }else{
                                    paginaAtual++;
                                }
                                break;
                            case 2:
                                if(paginaAtual > 1){
                                    paginaAtual--;
                                }else{
                                    System.out.println("Voce esta na primeira pagina");
                                }
                                break;
                            case 3:
                                Continue = false;
                                break;
                            default:
                                System.out.println("Opção invalida");
                        }

                    }
                    break;
                case 3:

                    System.out.println("Digite o ID que você quer deletar:");
                    idUsuario = type.nextInt();
                    type.nextLine();
                    System.out.println("tem certeza que deseja deletar o Usuaio (S para Sim/N) para Não");
                    String confirmacao = type.nextLine();

                    if(confirmacao.equalsIgnoreCase("S")){
                        repository.deletePorId(idUsuario);
                    }else{
                        System.out.println("Operção cancelada");
                    }

                    break;
                case 4:
                    System.out.println("Digite o ID do usuário que quer alterar:");
                    idUsuario = type.nextInt();
                    type.nextLine();

                    Usuario usuarioEncontrado = repository.buscarPorId(idUsuario);

                    if (usuarioEncontrado != null) {

                        int opcao2 = 0;

                        while (opcao2 != 4) {

                            System.out.println("\n===== MENU ATUALIZAÇÃO =====");
                            System.out.println("1 - Atualizar Nome");
                            System.out.println("2 - Atualizar Gmail");
                            System.out.println("3 - Atualizar Senha");
                            System.out.println("4 - Sair");

                            opcao2 = type.nextInt();
                            type.nextLine();

                            switch (opcao2) {

                                case 1:
                                    System.out.println("Novo nome:");
                                    String novoNome = type.nextLine();
                                    repository.atualizarNome(idUsuario, novoNome);
                                    break;

                                case 2:
                                    System.out.println("Novo gmail:");
                                    String novoGmail = type.nextLine();
                                    repository.atualizarGmail(idUsuario, novoGmail);
                                    break;

                                case 3:
                                    System.out.println("Nova senha:");
                                    String novaSenha = type.nextLine();
                                    repository.atualizarSenha(idUsuario, novaSenha);
                                    break;

                                case 4:
                                    System.out.println("Saindo do menu de atualização...");
                                    break;

                                default:
                                    System.out.println("Opção inválida!");
                            }
                        }

                    } else {
                        System.out.println("Usuário não encontrado.");
                    }

                    break;

                case 5:

                    System.out.println("Sistema Encerrado");

                    break;
                default:

                    System.out.println("Essa Opção não existe.");
            }

        }
    }
}

