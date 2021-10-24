package hu.uni.miskolc.teszteles.dao;

import java.util.Collection;

import hu.uni.miskolc.teszteles.core.Auto;
import hu.uni.miskolc.teszteles.service.exception.AutoNemTalalhato;
import hu.uni.miskolc.teszteles.service.exception.RendszamMarFoglalt;

public interface AutoDao {
	
	public Collection<Auto> readAllAutos();
	
	public Auto readAutoById(String rendszam) throws AutoNemTalalhato;
	
	public void createAuto(Auto auto) throws RendszamMarFoglalt;

	public void updateAuto(Auto auto);
	
	public void deleteAuto(Auto auto);
	
	public void deleteAutoById(String rendszam);
}
