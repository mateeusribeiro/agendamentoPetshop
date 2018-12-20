/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alunos.agendamentopetshop.model.negocio;

import com.alunos.agendamentopetshop.model.dao.ClienteDaoImpl;
import com.alunos.agendamentopetshop.model.interfaces.InterfaceCliente;
import com.alunos.agendamentopetshop.model.entidades.Cliente;

import com.alunos.agendamentopetshop.util.Criptografia;
import com.alunos.agendamentopetshop.util.TratamentoException;
import com.alunos.agendamentopetshop.util.ValidaCPF;
import java.util.List;

/**
 *
 * @author izaquiel cavalcante da silva izaquiel_cavalcante@hotmail.com
 */
public class ClienteModel implements InterfaceCliente<Cliente> {

    private ClienteDaoImpl repositorioCliente;
    private Cliente cliente;

    public ClienteModel() {
        repositorioCliente = new ClienteDaoImpl();
    }

    @Override
    public Cliente autenticar(String login, String senha) {
        if (login == null || senha == null) {
            return null;
        }
        return ((ClienteDaoImpl) repositorioCliente).autenticar(login, senha);
    }

    @Override
    public Cliente buscarCpf(String cpf) {
        if (cpf == null) {
            return null;
        } else {
            cliente = repositorioCliente.buscarCpf(cpf);
            if (cliente == null) {
                return null;
            } else {
                return cliente;
            }
        }
    }

    @Override
    public void salvar(Cliente cliente) throws Exception {

        boolean status = ValidaCPF.isCPF(cliente.getCpf());
        if (status == true) {
            if (cliente == null ) {
                TratamentoException.trataSalvar();
            }else if( buscarCpf(cliente.getCpf()) != null){
                
            }
            cliente.setSenha(Criptografia.criptografar(cliente.getSenha()));
            repositorioCliente.salvar(cliente);
        } else {
            throw new Exception("Erro!");
        }
    }

    @Override
    public Cliente buscar(Integer codigo) {
        if (codigo == null) {
            return null;
        } else {
            cliente = repositorioCliente.buscar(codigo);
            if (cliente == null) {
                return null;
            } else {
                return cliente;
            }
        }
    }

    @Override
    public void editar(Cliente cliente) throws Exception {
        if (cliente == null || buscar(cliente.getIdCliente()) == null) {
            throw new Exception("Erro");
        } else {
            repositorioCliente.editar(cliente);
        }
    }

    @Override
    public void deletar(Cliente cliente) throws Exception {
        if (cliente == null || buscarCpf(cliente.getCpf()) == null) {
            throw new Exception("Erro");
        } else {
            repositorioCliente.deletar(cliente);
        }
    }

    @Override
    public List<Cliente> listar() {
        List lista = repositorioCliente.listar();
        if (lista == null) {
            return null;
        } else {
            return lista;
        }
    }
}
