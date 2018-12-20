/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alunos.agendamentopetshop.model.interfaces;

/**
 *
 * @author Izaquiel
* @param <Empresa>
 */
public interface InterfaceEmpresa<Empresa> extends InterfaceGenerica<Empresa> {
    
    public Empresa autenticar(String login, String senha);
    public Empresa buscarCnpj(String cnpj);

}
