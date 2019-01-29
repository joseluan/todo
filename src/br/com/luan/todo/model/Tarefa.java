package br.com.luan.todo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tarefa implements Serializable{
	private static final long serialVersionUID = -9047397569324504878L;

	@Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;
    
    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Situacao situacao;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Situacao getSituacao() {
		return situacao;
	}


	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

}
