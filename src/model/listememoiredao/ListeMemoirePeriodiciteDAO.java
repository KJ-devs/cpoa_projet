package model.listememoiredao;

import java.util.ArrayList;
import java.util.List;

import model.idao.PeriodiciteIDAO;
import model.metier.Periodicite;

public class ListeMemoirePeriodiciteDAO implements PeriodiciteIDAO<Periodicite> {

		private static ListeMemoirePeriodiciteDAO instance;
		private List<Periodicite> Periodicite = new ArrayList<>();
		private List<Periodicite> donnees;


		public static ListeMemoirePeriodiciteDAO getInstance() {

			if (instance == null) {
				instance = new ListeMemoirePeriodiciteDAO();
			}

			return instance;
		}

		private ListeMemoirePeriodiciteDAO() {

			this.donnees = new ArrayList<>();
			this.donnees.add(new Periodicite(1, "Quotidien"));
			this.donnees.add(new Periodicite(2, "Hebdomadaire"));
			this.donnees.add(new Periodicite(3, "Mensuel"));
			this.donnees.add(new Periodicite(4, "Trimestriel"));
			this.donnees.add(new Periodicite(5, "Semestriel"));
			this.donnees.add(new Periodicite(6, "Annuel"));

		}


		@Override
		public boolean create(Periodicite objet) {

			objet.setId(3);
			// Ne fonctionne que si l'objet métier est bien fait...
			while (this.donnees.contains(objet)) {

				objet.setId(objet.getId() + 1);
			}

			return this.donnees.add(objet);
		}

		@Override
		public boolean update(Periodicite objet) {

			int idx = -1;
			for (Periodicite periodicite : this.donnees) {
				if (periodicite.getId() == objet.getId()) {
					idx = this.donnees.indexOf(periodicite);
				}
			}
			if (idx == -1) {
				throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
			} else {
				this.donnees.set(idx, objet);
				return true;
			}
		}

		@Override
		public boolean delete(Periodicite objet) {

			Periodicite supprime;

			// Ne fonctionne que si l'objet métier est bien fait...
			int idx = this.donnees.indexOf(objet);
			if (idx == -1) {
				throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
			} else {
				supprime = this.donnees.remove(idx);
			}

			return objet.equals(supprime);
		}

		@Override
		public Periodicite getById(int id) {
			// Ne fonctionne que si l'objet métier est bien fait...
			int idx = this.donnees.indexOf(new Periodicite(id,null));
			if (idx == -1) {
				throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
			} else {
				return this.donnees.get(idx);
			}
		}


		@Override
		public List<Periodicite> getAll() {
			// TODO Auto-generated method stub
			return this.donnees;
		}

		@Override
		public List<Periodicite> getByLib(String libelle) {
			for (Periodicite Perio:donnees ) {
				if(Perio.getLibelle().equalsIgnoreCase(libelle)) {
					Periodicite.add(Perio);
				}
			}
			return Periodicite;
		}

	}




