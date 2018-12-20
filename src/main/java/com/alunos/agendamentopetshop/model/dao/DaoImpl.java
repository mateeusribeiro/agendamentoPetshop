/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alunos.agendamentopetshop.model.dao;

import com.alunos.agendamentopetshop.model.interfaces.InterfaceDao;
import com.alunos.agendamentopetshop.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.alunos.agendamentopetshop.model.interfaces.InterfaceGenerica;

/**
 *
 * @author izaquiel cavalcante da silva izaquiel_cavalcante@hotmail.com
 */
public class DaoImpl implements InterfaceDao<Object> {

    private final SessionFactory sessionFactory;
    private static DaoImpl instance = null;

    public static DaoImpl getInstance() {
        if (instance == null) {
            instance = new DaoImpl();
        }
        return instance;
    }

    private DaoImpl() {
        this.sessionFactory = (SessionFactory) HibernateUtil.getSessionFactory();
    }

    @Override
    public void salvar(Object o) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;

        try {
            transacao = session.beginTransaction();
            session.save(o);
            transacao.commit();
        } catch (RuntimeException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }

    }

    @Override
    public List<Object> listar(String sql) {
        Session session = this.sessionFactory.openSession();
        List lista = null;

        try {
            Query consulta = session.createQuery(sql);
            lista = (List) consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }

        return lista;

    }

    @Override
    public void editar(Object o) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;

        try {
            transacao = session.beginTransaction();
            session.update(o);
            transacao.commit();
        } catch (RuntimeException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }

    }

    @Override
    public void deletar(Object o) {
        Session session = this.sessionFactory.openSession();
        Transaction transacao = null;

        try {
            transacao = session.beginTransaction();
            session.delete(o);
            transacao.commit();
        } catch (RuntimeException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }

    }

    public Object autenticar(String sql, String email, String senha) {
        Session session = this.sessionFactory.openSession();
        Object obj = null;

        try {
            Query consulta = session.createQuery(sql + " WHERE a.email = :email AND a.senha = :senha");
            consulta.setString("email", email);
            consulta.setString("senha", senha);
            obj = consulta.uniqueResult();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            session.close();
        }

        return obj;

    }

}
