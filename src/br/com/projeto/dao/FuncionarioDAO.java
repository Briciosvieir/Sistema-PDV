
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Funcionarios;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 *
 * @author fabriciovieira
 */
public class FuncionarioDAO {

private Connection con;

public FuncionarioDAO(){
this.con = new ConnectionFactory().getConnection();
}

//######################## CREATE #############################################
public void cadastrarFuncionario(Funcionarios obj){
    try {
         
 String sql ="INSERT INTO tb_funcionarios (nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
 
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getAcesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());
            
            stmt.execute();
            stmt.close();
            con.close();
            
            JOptionPane.showMessageDialog(null, "Funcionário Cadastrado");
            
            
    } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null,"ERRO no cadastro de funcionários:");
    }
   
   
}


//######################## READ ###############################################
    public List<Funcionarios>listarFuncionarios(){
        try {
            
            List<Funcionarios> lista = new ArrayList<>();
            
            String sql ="SELECT * FROM tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionarios obj = new Funcionarios();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setAcesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                lista.add(obj);
            
            
            }
            return lista;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"ERROR ao buscar a lista de Funcionários: ");
        }
    return null;
    }

 
    

//######################## UPDATE #############################################
public void editaFuncionario(Funcionarios obj){

    try {
        String sql ="UPDATE tb_funcionarios SET nome=?,rg=?,cpf=?,email=?,senha=?,cargo=?,nivel_acesso=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?, estado=? WHERE id=?";
        
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha()); 
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getAcesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());
            stmt.setInt(17, obj.getId());
            
            stmt.execute();
            stmt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso");
            
    } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"ERRO: ao editar");
    }
}


//######################## DELETE #############################################
public void excluirFuncionario(Funcionarios obj){
            
    try {
            String sql ="DELETE FROM tb_funcionarios WHERE id=?";
        
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            con.close();
            
            JOptionPane.showMessageDialog(null, "Deletado com sucesso");
            
            
    } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Error ao Excluir");
    }
        
}

    
            //METODO QUE PESQUISA CLIENTE E RETORNA UMA LISTA.
       public List<Funcionarios>buscaVariosFuncionariosPorNome(String nome){
        try {
           List<Funcionarios>lista = new ArrayList<>(); 
           
           String sql ="SELECT * FROM tb_clientes where nome like ?";
           PreparedStatement stmt = con.prepareStatement(sql);
           
           stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){ //next() é o que percorre a lista.
                Funcionarios obj = new Funcionarios();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setAcesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                lista.add(obj);
            }
            return lista;
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"ERRO" + e); 
             return null;
            
        }
        
    }   //  RETORNA APENAS 1 PESSOA POR NOME
       public Funcionarios pesquisaDeUmFuncionario(String nome){
           
           try { 
               String sql ="SELECT * FROM tb_clientes where nome = ?";
               PreparedStatement stmt = con.prepareStatement(sql);
               
               stmt.setString(1, nome);
               ResultSet rs = stmt.executeQuery();
               
               if(rs.next()){
                   Funcionarios obj = new Funcionarios();
                   obj.setId(rs.getInt("id"));
                   obj.setNome(rs.getString("nome"));
                   obj.setRg(rs.getString("rg"));
                   obj.setCpf(rs.getString("cpf"));
                   obj.setEmail(rs.getString("email"));
                   obj.setSenha(rs.getString("senha"));
                   obj.setCargo(rs.getString("cargo"));
                   obj.setAcesso(rs.getString("nivel_acesso"));
                   obj.setTelefone(rs.getString("telefone"));
                   obj.setCelular(rs.getString("celular"));
                   obj.setCep(rs.getString("cep"));
                   obj.setEndereco(rs.getString("endereco"));
                   obj.setNumero(rs.getInt("numero"));
                   obj.setComplemento(rs.getString("complemento"));
                   obj.setBairro(rs.getString("bairro"));
                   obj.setCidade(rs.getString("cidade"));
                   obj.setEstado(rs.getString("estado"));
                   
                return obj;   
               }
               
               
           } catch (SQLException e) {
               JOptionPane.showMessageDialog(null,"ERRO: "+ e);
              
           }
        return null;
       }
       


}
