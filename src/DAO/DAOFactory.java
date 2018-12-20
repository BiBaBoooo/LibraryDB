package DAO;

public class DAOFactory {
	private static DAOFactory instance;	
	static{
		instance = new DAOFactory();
	}	
	private DAOFactory(){
	}	
	public static DAOFactory getInstance(){
		return instance;
	}	
	public static KindBookDAO getKindBookDAO() {
		KindBookDAO k=new KDAOConcrete();
		return k;
	}
	public static SepcificBookDAO getSpecificBookDAO() {
		SepcificBookDAO k=new SDAOConcrete();
		return k;
	}
}
