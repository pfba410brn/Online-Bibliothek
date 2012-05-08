package db;

import java.util.List;

public class DbTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		Medium m = new Medium();
//	    m.setMediumId(3);
//	    m.setBezeichnung("Test1");
		
		DbVerwaltung db = new DbVerwaltung();
	    
		
	    List<Medium>resultList = db.selectAll_Medium();
        for(Medium m:resultList){
            System.out.println("name="+m.getBezeichnung());
        }
	}

}
