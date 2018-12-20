/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alunos.agendamentopetshop.model.interfaces;

import java.util.List;

/**
 *
 * @author 20161d13gr0112
 * @param <O>
 */
public interface InterfaceDao<O> {

    public void salvar(O o);

    public void editar(O o);

    public void deletar(O o);

    public List<O> listar(String sql);

    public Object autenticar(String sql, String email, String senha);
}
