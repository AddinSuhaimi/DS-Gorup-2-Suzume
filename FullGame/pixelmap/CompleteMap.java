/**
 *
 * @author HP
 */
public class CompleteMap {
    
     mapObj map1 = new mapObj("image 1");
     mapObj map2 = new mapObj("image 2");
     mapObj map3 = new mapObj("image 3");
     mapObj map4 = new mapObj("image 4");
    
     int[][] fin = new int[40][20];

    public CompleteMap() {
        replace();
        fullMapArray();
    }
    
    public void fullMapArray(){

        for(int i=0;i<20;i++){
            for(int j=0;j<10;j++) {
                fin[i][j] = map1.getarray()[j][i];
                fin[i][j+10] = map2.getarray()[j][i];
                fin[i+20][j] = map3.getarray()[j][i];
                fin[i+20][j+10] = map4.getarray()[j][i];
            }
           
        }
    }

    public int[][] getFullMap() {
        return fin;
    }
    
    public void replace(){            
        map1.correction();
        map2.correction();
        map3.correction();
        //No need to correct 4th map bottom right corner
    }
    
    
    
}
