import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

/*
 	Nome: Kaik Persike Maiorquino
 	Prontuario: CB3029689
 	
 	Nome: Matheus Penteado
 	Prontuario: CB3031501
*/

public class Form extends JFrame {
	private JTextField txtNomePesquisa;
	private JLabel lblId, lblNome, lblIdade, lblPeso, lblAltura;
	private JTextField txtId, txtNome, txtIdade, txtPeso, txtAltura;
	private JButton btnPesquisar, btnIncluir, btnLimpar, btnMostrar, btnCredito, btnSair;
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet resultSet;

	public Form() {
		setTitle("Prova 2");
		setLayout(null);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblPesquisa = new JLabel("Nome:");
        lblPesquisa.setBounds(20, 20, 50, 20);
        add(lblPesquisa);

        txtNomePesquisa = new JTextField();
        txtNomePesquisa.setBounds(80, 20, 200, 20);
        add(txtNomePesquisa);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(290, 20, 100, 20);
		add(btnPesquisar);

		lblId = new JLabel("ID:");
		lblId.setBounds(20, 60, 50, 20);
		add(lblId);

		txtId = new JTextField();
		txtId.setBounds(80, 60, 200, 20);
		txtId.setEditable(false);
		add(txtId);

		lblNome = new JLabel("Nome");
		lblNome.setBounds(20, 100, 50, 20);
		add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(80, 100, 200, 20);
		add(txtNome);

		lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(20, 140, 50, 20);
		add(lblIdade);

		txtIdade = new JTextField();
		txtIdade.setBounds(80, 140, 200, 20);
		add(txtIdade);
		
		lblPeso = new JLabel("Peso");
		lblPeso.setBounds(20, 180, 50, 20);
		add(lblPeso);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(80, 180, 200, 20);
		add(txtPeso);
		
		lblAltura = new JLabel("Altura");
		lblAltura.setBounds(20, 220, 50, 20);
		add(lblAltura);
		
		txtAltura = new JTextField();
		txtAltura.setBounds(80, 220, 200, 20);
		add(txtAltura);

		btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(20, 250, 100, 30);
		add(btnIncluir);

		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(150, 250, 100, 30);
		add(btnLimpar);
		
		btnMostrar = new JButton("Apresenta Dados");
		btnMostrar.setBounds(20, 300, 100, 30);
		add(btnMostrar);
		
		btnCredito = new JButton("Crédito");
		btnCredito.setBounds(150, 300, 100, 30);
		add(btnCredito);

		btnSair = new JButton("Sair");
		btnSair.setBounds(20,350, 100, 30);
		add(btnSair);
		
		btnPesquisar.addActionListener(e -> pesquisar());
		btnIncluir.addActionListener(e -> incluir());
		btnLimpar.addActionListener(e -> limpar());
		btnMostrar.addActionListener(e -> apresentaDados());
		btnCredito.addActionListener(e -> credito());
		btnSair.addActionListener(e -> sair());

		setVisible(true);
	}
	
	// conexão com o banco de dados
	private Connection conectar() throws SQLException {
	    String url = "jdbc:mysql://localhost:3306/dbp2";
	    String user = "root"; // ajuste conforme seu usuário
	    String password = ""; // ajuste conforme sua senha
	    return DriverManager.getConnection(url, user, password);
	}
	
	// funcao responsavel por isnerir valores dos campos nas respectivas colunas da tebela tb_user
	private void incluir() {
	    try (Connection conn = conectar()) {
	        String sql = "INSERT INTO tb_user (nm_nome, nr_idade, nr_peso, nr_altura) VALUES (?, ?, ?, ?)";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, txtNome.getText());
	        ps.setInt(2, Integer.parseInt(txtIdade.getText()));
	        ps.setDouble(3, Double.parseDouble(txtPeso.getText()));
	        ps.setDouble(4, Double.parseDouble(txtAltura.getText()));
	        ps.executeUpdate();
	        JOptionPane.showMessageDialog(this, "Usuário incluído com sucesso!");
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Erro ao incluir: " + ex.getMessage());
	    }
	}
	
	private void limpar() {
	    txtId.setText("");
	    txtNome.setText("");
	    txtIdade.setText("");
	    txtPeso.setText("");
	    txtAltura.setText("");
	}
	// funcao de apresentas dados
	private void apresentaDados() {
	    try (Connection conn = conectar()) {
	        String sql = "SELECT * FROM tb_user";
	        ps = conn.prepareStatement(sql);
	        resultSet = ps.executeQuery();
	        
	        // percebe-se que os dados são apresentados em forma de tabela usando |
	        StringBuilder sb = new StringBuilder();
	        while (resultSet.next()) {
	            sb.append("ID: ").append(resultSet.getInt("cd_id"))
	              .append(" | Nome: ").append(resultSet.getString("nm_nome"))
	              .append(" | Idade: ").append(resultSet.getInt("nr_idade"))
	              .append(" | Peso: ").append(resultSet.getDouble("nr_peso"))
	              .append(" | Altura: ").append(resultSet.getDouble("nr_altura"))
	              .append("\n");
	        }
	        JOptionPane.showMessageDialog(this, sb.toString());
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Erro ao apresentar dados: " + ex.getMessage());
	    }
	}
	
	// funcao de pesquisar
	private void pesquisar() {
	    try (Connection conn = conectar()) {
	        String sql = "SELECT * FROM tb_user WHERE nm_nome LIKE ?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, "%" + txtNomePesquisa.getText() + "%"); // usa o campo de pesquisa
	        resultSet = ps.executeQuery();

	        if (resultSet.next()) {
	            txtId.setText(String.valueOf(resultSet.getInt("cd_id")));
	            txtNome.setText(resultSet.getString("nm_nome"));
	            txtIdade.setText(String.valueOf(resultSet.getInt("nr_idade")));
	            txtPeso.setText(String.valueOf(resultSet.getDouble("nr_peso")));
	            txtAltura.setText(String.valueOf(resultSet.getDouble("nr_altura")));
	        } else {
	            JOptionPane.showMessageDialog(this, "Usuário não encontrado!");
	        }
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Erro na pesquisa: " + ex.getMessage());
	    }
	}
	
	// funcao de crédito
	private void credito() { 
	    JPanel painelCredito = new JPanel();
	    painelCredito.setLayout(null);
	    painelCredito.setBounds(20, 400, 450, 200);

	    JLabel lblTitulo = new JLabel("Créditos");
	    lblTitulo.setBounds(10, 10, 200, 20);
	    painelCredito.add(lblTitulo);

	    // este JLabel contem uma tag <html> responsável por deixar a formatação em ordem 
	    JLabel lblAutores = new JLabel(
	    		"<html>"
	    				+ "Nome: Kaik Persike Maiorquino<br>"
	    				+ "Prontuário: CB3029689<br><br>"
	    				+ "Nome: Matheus Penteado<br>"
	    				+ "Prontuário: CB3031501"
	    				+ "</html>"
	    		);

	    lblAutores.setBounds(10, 40, 400, 120); 
	    painelCredito.add(lblAutores);

	    JLabel lblDisciplina = new JLabel("Disciplina: Linguagem de Programação 2");
	    lblDisciplina.setBounds(10, 170, 400, 30); 
	    painelCredito.add(lblDisciplina);

	    add(painelCredito);
	    repaint(); // atualiza a tela
	}
	
	private void sair() {
	    System.exit(0);
	}
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Form::new);
    }
}