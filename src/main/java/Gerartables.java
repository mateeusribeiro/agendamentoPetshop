
import com.alunos.agendamentopetshop.model.dao.AgendamentoDaoImpl;
import com.alunos.agendamentopetshop.model.dao.ClienteDaoImpl;
import com.alunos.agendamentopetshop.model.dao.EmpresaDaoImpl;
import com.alunos.agendamentopetshop.model.dao.ServicoDaoImpl;
import com.alunos.agendamentopetshop.model.entidades.Agendamento;
import com.alunos.agendamentopetshop.model.entidades.Cliente;
import com.alunos.agendamentopetshop.model.entidades.Empresa;
import com.alunos.agendamentopetshop.model.entidades.Servico;
import com.alunos.agendamentopetshop.model.entidades.StatusAgen;
import com.alunos.agendamentopetshop.model.negocio.AgendamentoModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matheus Ribeiro
 */
public class Gerartables {
      public static void main (String [] args){
           EmpresaDaoImpl empDao = new EmpresaDaoImpl();
           
        Empresa empresa = new Empresa("61471683000150", "12345678", "falabixo@gmail.com", "99999999999", "falabixo", null);
        empDao.salvar(empresa);
        ClienteDaoImpl cliDao = new ClienteDaoImpl();
         Cliente cliente = new Cliente("zaza", "16641035471", "981155288", null, "zaza@gmail.com", "1234");

        cliDao.salvar(cliente);

        ServicoDaoImpl serviDao = new ServicoDaoImpl();

        Servico servi = new Servico("toza", 30, null, empresa, null);

        serviDao.salvar(servi);

        AgendamentoDaoImpl agenDao = new AgendamentoDaoImpl();

        Agendamento agenda = new Agendamento(servi, cliente, null, null, StatusAgen.REALIZADO);
       agenDao.salvar(agenda);
          AgendamentoModel model = new AgendamentoModel();
       for(Agendamento age : model.listar()){
           System.out.println("Agendamentos listados abaixo:\n"+ age.getCliente());
       }
      }
}
