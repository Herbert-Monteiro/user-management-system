package org.example.model;

import java.time.LocalDate;
public class Usuario {
    private int idUsuario;
    private String Nome;
    private LocalDate Data_nascimento;
    private String rg;
    private String gmail;
    private String senha;
    private String cpf;
    private LocalDate data_cadastro;
    public Usuario() {
        this.idUsuario = idUsuario;
        this.Nome = Nome;
        this.Data_nascimento = Data_nascimento;
        this.rg = rg;
        this.gmail = gmail;
        this.senha = senha;
        this.cpf = cpf;
        this.data_cadastro = data_cadastro;
    }
    public String getNome() {
        return Nome;
    }
    public LocalDate getData_nascimento() {
        return Data_nascimento;
    }
    public String getRg() {
        return rg;
    }
    public String getgmail() {
        return gmail;
    }
    public String getSenha() {
        return senha;
    }
    public String getcpf() {
        return cpf;
    }
    public  int getIdUsuario(){return idUsuario;}

    public LocalDate getData_cadastro() {
        return data_cadastro;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        Data_nascimento = data_nascimento;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setgmail(String gmail) {
        this.gmail = gmail;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setcpf(String cpf) {
        this.cpf = cpf;
    }

    public void setData_cadastro(LocalDate data_cadastro) {
        this.data_cadastro = data_cadastro;
    }
}


