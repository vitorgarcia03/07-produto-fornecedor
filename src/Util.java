import java.text.DecimalFormat;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;


public class Util {
    private Produto[] produto = new Produto[5];
    private Fornecedor[] fornecedor = new Fornecedor[5];
    private int idxProduto = 0;
    private int idxFornecedor = 0;

    public void menu(){
        int opcao;
        String msg = "1. Cadastrar produto\n2. Pesquisar produto" +
                     "\n3. Pesquisar fornecedor\n4. Finalizar";

        while(true){
            opcao = parseInt(showInputDialog(msg));
            if(opcao == 4){
                return;
            }
            switch (opcao){
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    pesquisarProduto();
                    break;
                case 3:
                    pesquisar();
                    break;
                default:
                    showMessageDialog(null, "Opção inválida");
            }
        }
    }

    private void pesquisar(){
        Fornecedor fornecedor = pesquisarFornecedor();
        if(fornecedor != null){
            String aux = "";
            aux += "Fornecedor: " + fornecedor.getNome() + "\n";
            aux += "CNPJ: " + fornecedor.getCnpj() + "\n";
            showMessageDialog(null, aux);
         }

    }

    private void cadastrarProduto(){
        String nome;
        double valorUnitario;
        int qtdEstoque;
        Fornecedor fornecedor = pesquisarFornecedor();

        if(fornecedor == null){
            fornecedor = cadastrarFornecedor();
        }

        nome = showInputDialog("Nome do produto");
        valorUnitario = parseDouble(showInputDialog("Valor unitário"));
        qtdEstoque = parseInt(showInputDialog("Quantidade em estoque"));
        produto[idxProduto] = new Produto(nome, valorUnitario, qtdEstoque, fornecedor);
        idxProduto++;
    }

    private Fornecedor cadastrarFornecedor() {
        Fornecedor fornecedor;
        String nome = showInputDialog("Nome do fornecedor");
        int cnpj = parseInt(showInputDialog("CNPJ"));
        fornecedor = new Fornecedor(nome, cnpj);
        this.fornecedor[idxFornecedor] = fornecedor;
        idxFornecedor++;
        return fornecedor;
    }

    private void pesquisarProduto(){
        DecimalFormat df = new DecimalFormat("0.00");
        String aux = "Produto não encontrado";
        String nome = showInputDialog("Nome do produto");
        for(int i = 0; i < idxProduto; i++){
            if(produto[i].getNome().equalsIgnoreCase(nome)) {
                aux = "";
                aux += "Nome do produto: " + nome + "\n";
                aux += "Valor unitário: R$" + df.format(produto[i].getValorUnitario()) + "\n";
                aux += "Fornecedor: " + produto[i].getFornecedor().getNome() + "\n";
            }
        }
        showMessageDialog(null, aux);
    }

    private Fornecedor pesquisarFornecedor(){
        int cnpj = parseInt(showInputDialog("CNPJ do fornecedor"));
        for (int i = 0; i < idxFornecedor; i++){
            if(fornecedor[i].getCnpj() == cnpj) {
                return fornecedor[i];
            }
        }
        showMessageDialog(null, cnpj + " não cadastrado");
        return null;
    }
}
