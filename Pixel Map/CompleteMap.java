package ds-group-2-suzume;

import java.util.Arrays;

/**
 *
 * @author HP
 */
public class CompleteMap {
    
     mapObj map1 = new mapObj("image1");//[10][20]
     mapObj map2 = new mapObj("image2");
     mapObj map3 = new mapObj("image3");
     mapObj map4 = new mapObj("image4");
    
     int[][] fin = new int[20][20];

    public CompleteMap() {
        replace();
        topPart();
        botPart();
    }
    
    public void topPart(){

        for(int i=0;i<10;i++){
            
            
            int[] firstArray = map1.getarray()[i];        //source array  
            int[] secondArray = map3.getarray()[i];  //destination array  
            
            int fal = firstArray.length;
            int sal = secondArray.length;   //determines length of secondArray  
            int[] result = new int[40];  //resultant array of size first array and second array  
            System.arraycopy(firstArray, 0, result, 0, 20);  
            System.arraycopy(secondArray, 0, result, 20, 20);  
            fin[i] = result;
        }
    }
    
    public void botPart(){
        for(int i=0;i<10;i++){
            int[] firstArray = map2.getarray()[i];        //source array  
            int[] secondArray = map4.getarray()[i];  //destination array  
            
            int fal = firstArray.length;
            int sal = secondArray.length;   //determines length of secondArray  
            int[] result = new int[40];  //resultant array of size first array and second array  
            System.arraycopy(firstArray, 0, result, 0, 20);  
            System.arraycopy(secondArray, 0, result, 20, 20);  
            fin[i+10] = result;
        }
    }

    public int[][] getFin() {
        return fin;
    }
    
    public void replace(){            
        map1.correction();
        map2.correction();
        map3.correction();
        
    }
    
    
    
}
