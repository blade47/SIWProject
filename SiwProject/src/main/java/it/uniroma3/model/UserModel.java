package it.uniroma3.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Email;

@Entity
public class UserModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull(message="Campo nullo")
	@Size(min=2, max=10, message="Il campo deve avere minimo 2 caratteri fino ad un massimo di 10")
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", message="L'username deve contenere almeno una lettera maiuscola, minuscola ed un numero")
	private String username;
	
	@NotNull(message="Campo nullo")
	@Email(message="Inserisci una e-mail valida")
    private String email;

	@NotNull(message="Campo nullo")
	@Size(min=6, max=20, message="Deve contenere almeno 6 caratteri e fino ad un massimo di 20")
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", message="La password deve contenere una lettera maiuscola, una minuscola e un numero")
	private String password;
	
	@ManyToOne
    private RuoloModel ruolo;
    
    @Size(max=20, message="Il campo deve avere fino ad un massimo di 20 caratteri")
    private String facebook;
    
    @Size(max=20, message="Il campo deve avere fino ad un massimo di 20 caratteri")
    private String instagram;
    
    @Size(max=20, message="Il campo deve avere fino ad un massimo di 20 caratteri")
    private String linkedin;
    
    private String avatar;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<PhotoModel> fotoVotate;
    
    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.REMOVE)
    private List<PhotoModel> foto;
    
    public List<PhotoModel> getFoto() {
		return foto;
	}
	public void setFoto(List<PhotoModel> foto) {
		this.foto = foto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public RuoloModel getRuolo() {
		return ruolo;
	}
	public void setRuolo(RuoloModel ruolo) {
		this.ruolo = ruolo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<PhotoModel> getFotoVotate() {
		return fotoVotate;
	}
	public void setFotoVotate(List<PhotoModel> fotoVotate) {
		this.fotoVotate = fotoVotate;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getInstagram() {
		return instagram;
	}
	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Override
	public boolean equals(Object ob){
		UserModel user = (UserModel) ob;
		return this.id == user.id;
	}
	@Override
	public int hashCode(){
		return this.id;
	}
	
}
