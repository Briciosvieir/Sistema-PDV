
package br.com.projeto.model;

/**
 *
 * @author fabriciovieira
 */
public class Funcionarios extends Clientes {
    private String senha;
    private String cargo;
    private String acesso;
    
    

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }
    
    
    
}
