/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luan.todo.mbean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.luan.todo.dao.TarefaDAO;
import br.com.luan.todo.model.Situacao;
import br.com.luan.todo.model.Tarefa;
/**
 *
 * @author Luan
 */
@ManagedBean
@ViewScoped
public class TarefaBean extends TarefaDAO implements Serializable{
	private static final long serialVersionUID = -7580566433471411911L;
	
	private Tarefa tarefa = new Tarefa();
    private List<Tarefa> tarefas = list();
    private Tarefa tarefaSelecionada = new Tarefa();
    private String lista_now = "todas";

    public String adicionar() throws InterruptedException {
        tarefa.setSituacao(Situacao.AGUARDANDO);
        persist(tarefa);
        tarefa = new Tarefa();
        tarefas = list();
        return null;
    }
    
    public String retirar(Tarefa tarefa_rm) {
    	remove(tarefa_rm);
    	tarefas = list();
    	return null;
    }
    
    public String alterar(Tarefa tarefa_rm) {
    	if(tarefa_rm.getSituacao() == Situacao.AGUARDANDO) {
    		tarefa_rm.setSituacao(Situacao.CONCLUIDA);
    	}else{
    		tarefa_rm.setSituacao(Situacao.AGUARDANDO);
    	}
    	merge(tarefa_rm);
    	tarefas = list();
    	return null;
    }
    
    public String corBotaoAlterar(Tarefa tarefa_rm) {
    	if(tarefa_rm.getSituacao() == Situacao.AGUARDANDO) {
    		return "btn-success";
    	}else {
    		return "btn-danger";
    	}
    }
    
    public String nomeBotaoAlterar(Tarefa tarefa_rm) {
    	if(tarefa_rm.getSituacao() == Situacao.AGUARDANDO) {
    		return "Concluir";
    	}else {
    		return "Cancelar";
    	}
    }
    
    public String mostrarTodasTarefas() {
    	tarefas = list();
    	this.lista_now = "todas";
    	return null;
    }
    
    public String mostrarTarefasConcluidas() {
    	tarefas = listConcluida();
    	this.lista_now = "concluidas";
    	return null;
    }
    
    public String mostrarTarefasAguardando() {
    	tarefas = listPendente();
    	this.lista_now = "pendentes";
    	return null;
    }
    
    public String selecionarTarefa(Tarefa t) {
        this.tarefaSelecionada = t;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('ModalTarefa').show();");
        return null;
    }
    
    public String atualizar() {
    	merge(this.tarefaSelecionada);
    	switch (this.lista_now) {
		case "pendentes":
			tarefas = listPendente();
			break;
		case "concluidas":
			tarefas = listConcluida();
			break;
		case "todas":
			tarefas = list();
			break;
		default:
			break;
		}
    	
    	this.tarefaSelecionada = null;
    	return null;
    }
    
    //GET E SETS
	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Tarefa getTarefaSelecionada() {
		return tarefaSelecionada;
	}

	public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
		this.tarefaSelecionada = tarefaSelecionada;
	}
    
}
