/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luan.todo.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.luan.todo.model.Situacao;
import br.com.luan.todo.model.Tarefa;

/**
 *
 * @author Luan
 */
public class TarefaDAO implements Serializable {
	private static final long serialVersionUID = -8892665506599113364L;
	private static EntityManager em = Database.getInstance().getEntityManager();
	public int quantidade_ativos() {
        Query query = em.createQuery("SELECT x.id FROM Tarefa x");
        int quantidade = query.getResultList().size();//Existe forma mais rapida do que isso
        return quantidade;
    }

    public void persist(Tarefa tarefa) {
        try {
            em.getTransaction().begin();
            em.persist(tarefa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
	public static List<Tarefa> list() {
        List<Tarefa> lista = null;
        try {
        	Query query = em.createQuery("SELECT x FROM Tarefa x");
        	lista = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
    @SuppressWarnings("unchecked")
	public static List<Tarefa> listConcluida() {
        List<Tarefa> lista = null;
        try {
        	Query query = em.createQuery("SELECT x FROM Tarefa x WHERE x.situacao = :stc");
        	query.setParameter("stc", Situacao.CONCLUIDA);
        	lista = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
    @SuppressWarnings("unchecked")
	public static List<Tarefa> listPendente() {
        List<Tarefa> lista = null;
        try {
        	Query query = em.createQuery("SELECT x FROM Tarefa x WHERE x.situacao = :stc");
        	query.setParameter("stc", Situacao.AGUARDANDO);
        	lista = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void merge(Tarefa tarefa) {
        try {
            em.getTransaction().begin();
            em.merge(tarefa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
    
    public void remove(Tarefa t) {
        try {
        	em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

}
