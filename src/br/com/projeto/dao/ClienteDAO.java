
package br.com.projeto.dao;


import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.WebServiceCep;
import java.sql.ResultSet;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO {
    private Connection con;
    
    
    public ClienteDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
            //Metodo Inserir no banco ##########
    public void cadastrarCliente(Clientes obj){
        try {
            String sql = "INSERT INTO tb_clientes (nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
            +" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getRg());
            stmt.setString(3,obj.getCpf());
            stmt.setString(4,obj.getEmail());
            stmt.setString(5,obj.getTelefone());
            stmt.setString(6,obj.getCelular());
            stmt.setString(7,obj.getCep());
            stmt.setString(8,obj.getEndereco());
            stmt.setInt(9,obj.getNumero());
            stmt.setString(10,obj.getComplemento());
            stmt.setString(11,obj.getBairro());
            stmt.setString(12,obj.getCidade());
            stmt.setString(13,obj.getEstado());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente Cadastrado!");
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null,"ERRO" + e);
        }
        
    }
    //Listar Todos os Clientes ######################
    public List<Clientes>listarCliente(){
        try {
           List<Clientes>lista = new ArrayList<>(); 
           
           String sql ="SELECT * FROM tb_clientes";
           PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){ //next() é o que percorre a lista.
                Clientes obj = new Clientes();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
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
        
    }
     // Metodo UPDATE  ##################
    public void alterarCliente(Clientes obj) {
        
        try {
        String sql ="UPDATE tb_clientes SET nome=?,rg=?,cpf=?,email=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? WHERE id =?";
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getRg());
        stmt.setString(3, obj.getCpf());
        stmt.setString(4, obj.getEmail());
        stmt.setString(5, obj.getTelefone());
        stmt.setString(6, obj.getCelular());
        stmt.setString(7, obj.getCep());
        stmt.setString(8, obj.getEndereco());
        stmt.setInt(9, obj.getNumero());
        stmt.setString(10, obj.getComplemento());
        stmt.setString(11, obj.getBairro());
        stmt.setString(12, obj.getCidade());
        stmt.setString(13, obj.getEstado());
        
        //Update você deve colocar o id para que a tabela seja alterada através do mesmo.
        stmt.setInt(14, obj.getId());
        
        stmt.execute();
        stmt.close();
        
        
            JOptionPane.showMessageDialog(null, "Dados Alterados!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        
    }
    
    //Metodo DELETE ###################
    public void excluirCliente(Clientes obj){
        
        try {
         String sql="DELETE FROM tb_clientes WHERE id = ? ";
         PreparedStatement stmt = con.prepareStatement(sql);
         stmt.setInt(1,obj.getId());
         stmt.execute();
         stmt.close();
         JOptionPane.showMessageDialog(null, "Deletado");
        } catch (HeadlessException | SQLException e) {     
            JOptionPane.showMessageDialog(null, "Erro:"+e);
            
        }
        
    }
    
    
            //METODO QUE PESQUISA CLIENTE E RETORNA UMA LISTA.
       public List<Clientes>buscaClientePorNome(String nome){
        try {
           List<Clientes>lista = new ArrayList<>(); 
           
           String sql ="SELECT * FROM tb_clientes where nome like ?";
           PreparedStatement stmt = con.prepareStatement(sql);
           
           stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){ //next() é o que percorre a lista.
                Clientes obj = new Clientes();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
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
        
    }   // retorna uma uma pessoa por nome
       public Clientes pesquisaDeUmCliente(String nome){
           
           try { 
               String sql ="SELECT * FROM tb_clientes where nome = ?";
               PreparedStatement stmt = con.prepareStatement(sql);
               
               stmt.setString(1, nome);
               ResultSet rs = stmt.executeQuery();
               
               if(rs.next()){
                   Clientes obj = new Clientes();
                   obj.setId(rs.getInt("id"));
                   obj.setNome(rs.getString("nome"));
                   obj.setRg(rs.getString("rg"));
                   obj.setCpf(rs.getString("cpf"));
                   obj.setEmail(rs.getString("email"));
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
       
       
       
       
       //Busca dados por CEP
       public Clientes buscaCep(String cep) {
       
           WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        Clientes obj = new Clientes();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setEstado(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }
	
}
