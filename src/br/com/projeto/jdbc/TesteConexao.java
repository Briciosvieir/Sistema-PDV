
package br.com.projeto.jdbc;

import javax.swing.JOptionPane;

public class TesteConexao {
    public static void main(String[] args) {
        try {
            
           new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso");
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"erro"+ erro);
        }
    }
    
}
