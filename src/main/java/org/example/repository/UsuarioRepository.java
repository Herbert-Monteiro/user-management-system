package org.example.repository;
import org.example.dataBase.Conexao;
import org.example.model.Usuario;
import org.example.security.SenhaHash;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    public boolean existeUsuario(){
        String sql = "SELECT COUNT(*) FROM usuario";
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            if(rs.next()){
                return rs.getInt(1) > 0;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean fazerLogin(String gmail, String senha) {
        String sql = "SELECT * FROM usuario WHERE gmail = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, gmail);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){

                String Senhabanco = rs.getString("senha");
                String SenhaDigital = SenhaHash.gerarHash(senha);

                return Senhabanco.equals(SenhaDigital);
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public void inserir(Usuario usuario) {

        String sql = "INSERT INTO usuario (nome, cpf, gmail, senha, rg, data_nascimento) VALUES (? ,? ,? ,? ,? ,? )";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String senhaHash = SenhaHash.gerarHash(usuario.getSenha());

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getcpf());
            stmt.setString(3, usuario.getgmail());
            stmt.setString(4,senhaHash);
            stmt.setString(5, usuario.getRg());
            stmt.setDate(6, Date.valueOf(usuario.getData_nascimento()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listaTabela(int pagina,int limite) {

        String sql = "SELECT * FROM usuario LIMIT ? OFFSET ?";

        List<Usuario> lista = new ArrayList<>();
        int offset = (pagina - 1) * limite;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,limite);
            stmt.setInt(2,offset);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario Usuario = new Usuario();

                Usuario.setIdUsuario(rs.getInt("idUsuario"));
                Usuario.setNome(rs.getString("nome"));
                Usuario.setcpf(rs.getString("CPF"));
                Usuario.setgmail(rs.getString("Gmail"));
                Usuario.setSenha(rs.getString("senha"));
                Usuario.setRg(rs.getString("rg"));
                Usuario.setData_nascimento(rs.getDate("data_nascimento").toLocalDate());
                Usuario.setData_cadastro(rs.getDate("data_cadastro").toLocalDate());

                lista.add(Usuario);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;

    }

    public int contarUsuarios(){
        String sql = "SELECT COUNT(*) FROM usuario";

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void deletePorId (int idUsuario){

        String sql = "DELETE FROM usuario WHERE idUsuario = ?";

        try (Connection conn = Conexao.conectar();
            PreparedStatement  stmt = conn.prepareStatement(sql)){

            stmt.setInt(1,idUsuario);

            int linhaAfetada = stmt.executeUpdate();

            if(linhaAfetada > 0){
                System.out.println("USUARIO FOI DELETADO COM SUCESSO!");
            }else {
                System.out.println("NEM UM USUARIO FOI ENCONTRADO COM O ID INSERIDO!");
            }
        }catch (SQLException e) {
            System.err.println("Erro ao deletar: " + e.getMessage());

    }
}

    public Usuario buscarPorId(int idUsuario){

        String sql = "SELECT * FROM usuario Where idUsuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1,idUsuario);

            ResultSet rs = stmt.executeQuery();


            if(rs.next()){
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setcpf(rs.getString("cpf"));
                usuario.setgmail(rs.getString("gmail"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setRg(rs.getString("rg"));
                usuario.setData_nascimento(
                        rs.getDate("data_nascimento").toLocalDate()
                );

                return usuario;

            }else{
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }}

    public void atualizarNome(int idUsuario, String novoNome) {

        String sql = "UPDATE usuario SET nome = ? WHERE idUsuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setInt(2, idUsuario);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Nome foi atualizado com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com esse ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarGmail(int idUsuario, String novoGmail) {

        String sql = "UPDATE usuario SET gmail = ? WHERE idUsuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoGmail);
            stmt.setInt(2, idUsuario);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Gmail foi atualizado com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com esse ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarSenha(int idUsuario, String novoSenha) {

        String sql = "UPDATE usuario SET senha = ? WHERE idUsuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String senhaHash = SenhaHash.gerarHash(novoSenha);
            stmt.setString(1, senhaHash);
            stmt.setInt(2, idUsuario);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Usuário foi atualizado com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com esse ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


