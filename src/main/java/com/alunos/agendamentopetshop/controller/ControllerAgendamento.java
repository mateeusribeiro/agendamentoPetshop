/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alunos.agendamentopetshop.controller;

import com.alunos.agendamentopetshop.model.entidades.Agendamento;
import com.alunos.agendamentopetshop.model.entidades.StatusAgen;
import com.alunos.agendamentopetshop.model.negocio.AgendamentoModel;
import com.alunos.agendamentopetshop.util.JavaMail;
import com.alunos.agendamentopetshop.util.Mensagens;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author izaquiel cavalcante da silva izaquiel_cavalcante@hotmail.com
 */
@ManagedBean
@SessionScoped
public class ControllerAgendamento implements Serializable {

    private AgendamentoModel negocioAgendamento;
    private Agendamento agendamento;
    private ArrayList<Agendamento> agendamentos;
    private String ret = "";

    public ControllerAgendamento() {
        negocioAgendamento = new AgendamentoModel();
        agendamento = new Agendamento();
        agendamentos = new ArrayList<>();
    }

    public String salvar() {
        try {
            agendamento.setStatusAgen(StatusAgen.ESPERA);
            negocioAgendamento.salvar(agendamento);
            JavaMail.getInstance().enviarEmail(agendamento, 1);
            ret = "Cliente/meusAgendamentos.xhtml?faces-redirect=true";
        } catch (Exception ex) {
            Mensagens.getInstance().nenhumaInformacao();
        }
        return ret;
    }

    public String buscar() {
        negocioAgendamento.buscar(agendamento.getId());
        return "Cliente/meusAgendamentos.xhtml?faces-redirect=true";
    }

    public String alterar() {
        try {
            negocioAgendamento.editar(agendamento);
            Mensagens.getInstance().alteradoComSucesso();
            JavaMail.getInstance().enviarEmail(agendamento, 2);
            ret = "Cliente/meusAgendamentos.xhtml?faces-redirect=true";
        } catch (Exception ex) {
            Mensagens.getInstance().nenhumaInformacao();
        }
        return ret;
    }

    public String deletar() {
        try {
            negocioAgendamento.deletar(agendamento);
            Mensagens.getInstance().deletadoComSucesso();
            ret = "Cliente/meusAgendamentos.xhtml?faces-redirect=true";
        } catch (Exception ex) {
            Mensagens.getInstance().nenhumaInformacao();
        }
        return ret;
    }

    public String listarAgendamentoCliente(int id) {
        agendamentos = new ArrayList<>();
        for (Agendamento ag : negocioAgendamento.listar()) {
            if (id == (ag.getCliente().getIdCliente())) {
                agendamentos.add(ag);
            }
        
    }
        return "Cliente/meusAgendamentos.xhtml?faces-redirect=true";
    }
    public String listarAgendamentoEmpresa(int id) {
        agendamentos = new ArrayList<>();
        for (Agendamento ag : negocioAgendamento.listar()) {
            if (id == (ag.getServico().getEmpresa().getIdEmpresa())) {
                agendamentos.add(ag);
            }
        
    }
        return "Empresa/meusAgendamentos.xhtml?faces-redirect=true";
    }
    public AgendamentoModel getNegocioAgendamento() {
        return negocioAgendamento;
    }

    public void setNegocioAgendamento(AgendamentoModel negocioAgendamento) {
        this.negocioAgendamento = negocioAgendamento;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public ArrayList<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(ArrayList<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

}

