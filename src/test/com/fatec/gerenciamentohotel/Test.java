package test.com.fatec.gerenciamentohotel;

import com.fatec.gerenciamentohotel.control.FuncionarioControl;
import com.fatec.gerenciamentohotel.entity.EFuncionario;
import com.fatec.gerenciamentohotel.entity.Endereco;
import com.fatec.gerenciamentohotel.entity.Funcionario;

public class Test {
    public static void main(String[] args) {

        Funcionario f = new Funcionario();
        f.setCpf("1234");
        f.setCelular("12345");
        f.setEmail("duasihduiasd");

        Endereco end = new Endereco();
        end.setCep("123");
        end.setBairro("dsadasd");
        end.setCidade("sp");
        end.setUf("SP");
        end.setRua("Rua qualquer");

        f.setEndereco(end);
        f.setId(1);
        f.setNome("Funcionario");
        f.setLogin("func1");
        f.setSenha("123");
        f.setStatus(false);
        f.setTelefone("151516");
        f.setTipoFuncionario(EFuncionario.SERV_DE_QUARTO);
        FuncionarioControl funcionarioControl = new FuncionarioControl();
        funcionarioControl.insert(f);
        try {
            System.out.println(funcionarioControl.selectDocFuncionario(f.getCpf()).getNome());
            System.out.println(funcionarioControl.selectDocFuncionario("125").getNome());
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Não há registros deste documento para este funcionário");
        }
    }
}
