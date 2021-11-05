package model.idao;

import java.util.List;

import model.dao.DAO;

public interface ClientIDAO<Client> extends DAO<Client> {
	List<Client> getByCodePostal(String code_postal);
	
    List<Client> getByNom(String nom);
    
    List<Client> getByNo_Rue(String no_rue);
    
    List<Client> getByPrenom(String prenom);
    
    List<Client> getByVille(String ville);
    
    List<Client> getByVoie(String voie);
    
    List<Client> getByPays(String pays);
}
