package it.uniroma3.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class PhotoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String link;

	private String descrizione;

	@ManyToMany(mappedBy = "fotoVotate", fetch = FetchType.EAGER)
	private List<UserModel> utentiVotanti;
	
	@ManyToOne
	private UserModel proprietario;
	
	private int numeroVoti;
	
	public int getNumeroVoti() {
		return numeroVoti;
	}
	public void setNumeroVoti(int numeroVoti) {
		this.numeroVoti = numeroVoti;
	}
	public UserModel getProprietario() {
		return proprietario;
	}
	public void setProprietario(UserModel proprietario) {
		this.proprietario = proprietario;
	}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public List<UserModel> getUtentiVotanti() {
		return utentiVotanti;
	}
	public void setUtentiVotanti(List<UserModel> utentiVotanti) {
		this.utentiVotanti = utentiVotanti;
	}

	@Override
	public boolean equals(Object ob){
		PhotoModel photo = (PhotoModel) ob;
		return this.id == photo.id;
	}
	@Override
	public int hashCode(){
		return this.id;
	}
	
}
