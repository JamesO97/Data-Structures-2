package app;

import cop3530_util.Timer;
import java.util.ArrayList;
import java.util.Random;


public class Controller {

    public static void main(String[] args) {
  
        Timer timer = new Timer();
        
        ArrayList<InfoNode> insertionSortNodes = new ArrayList<>();
        ArrayList<InfoNode> quickSortNodes = new ArrayList<>();
        ArrayList<InfoNode> mergeSortNodes = new ArrayList<>();
        
        InfoNode infoNode = null;
        int arrayLength = 10;
        
        int[] baseData = new int[arrayLength];
        
        for(int j=0 ;j<baseData.length; j++){
                    baseData[j] = j+1;
        }
        
        timer.start();
        insertionSort(baseData);    
        timer.end();
        
        insertionSortNodes.add(new InfoNode(1, baseData.length, analyzeArrayDistribution(baseData, arrMedian(baseData)), timer.millSeconds(), "Insertion Sort"));
        
        
        // for loop to shuffle and save to report
        for(int k=0; k <= 8; ++k){
            shuffleArray(baseData);
            timer.start();
            insertionSort(baseData);
            timer.end();
            insertionSortNodes.add(new InfoNode(k+2, baseData.length, analyzeArrayDistribution(baseData, arrMedian(baseData)), timer.nanoSeconds(), "Quick Sort"));
            // save data
        }
        
        report(insertionSortNodes);
        timer.start();
        quickSort(baseData, 0, baseData.length-1);
        timer.end();
        quickSortNodes.add(new InfoNode(1, baseData.length, analyzeArrayDistribution(baseData, arrMedian(baseData)), timer.millSeconds(), "Quick Sort"));
    
        // for loop to shuffle and save to report
        for(int k=0; k <= 8; ++k){
            shuffleArray(baseData);
            timer.start();
            quickSort(baseData, 0, baseData.length-1);
            timer.end();
            quickSortNodes.add(new InfoNode(k+2, baseData.length, analyzeArrayDistribution(baseData, arrMedian(baseData)), timer.nanoSeconds(), "Merge Sort"));
            // save data
        }
        
        report(quickSortNodes);
        timer.start();
        mergeSort(baseData, 0, baseData.length-1);
        timer.end();
        mergeSortNodes.add(new InfoNode(1, baseData.length, analyzeArrayDistribution(baseData, arrMedian(baseData)), timer.millSeconds(), "Merge Sort"));
         
        // for loop to shuffle and save to report
        for(int k=0; k <= 8; ++k){
            shuffleArray(baseData);
            timer.start();
            mergeSort(baseData, 0, baseData.length-1);
            timer.end();
            mergeSortNodes.add(new InfoNode(k+2, baseData.length, analyzeArrayDistribution(baseData, arrMedian(baseData)), timer.nanoSeconds(), "Insertion Sort"));
            // save data
        }
        
        report(mergeSortNodes);

    }//end main
    
    public static void report(ArrayList<InfoNode> sortNodes){
        double avgRun = 0;
        double avgDist = 0.0;
        
        System.out.printf("Run  Sort Name     Length        Dist      Runtime\n");
        
        System.out.println("============================================================================================");

        for(int i = 0; i < sortNodes.size(); ++i) {
            System.out.printf("%-4d",sortNodes.get(i).getRun());
            System.out.printf(" %-12s",sortNodes.get(i).getSortName());
            System.out.printf("  %-10d",sortNodes.get(i).getArrayLength());
            System.out.printf("  %-1.5f",sortNodes.get(i).getDist());
            System.out.printf("    %-7d\n",sortNodes.get(i).getRunTime());
            
            avgDist += sortNodes.get(i).getDist();
            avgRun += sortNodes.get(i).getRunTime();
        }
        System.out.println("============================================================================================");
        
        avgDist /= (double)sortNodes.size();
        avgRun /= (double)sortNodes.size();
        
        System.out.printf("\t\t\tAverage:%-1.5f", avgDist);
        System.out.printf("    %-1.5f\n\n", avgRun);
    }

    //===========================================================================
    public static void shuffleArray(int[] arr){
        Random random = new Random();
        
        random.nextInt();
        
        for(int i = 0; i< arr.length; i++){
            int change = i + random.nextInt(arr.length -i);
            int temp = arr[i];
            arr[i] = arr[change];
            arr[change]= temp;
        }//end for
    }//end shuffleArray
    
    public static void displayArray(int[] arr){
        for(int element: arr){
            System.out.print(element);
            if(element != arr[arr.length-1])
                System.out.print(" ");
            else
                System.out.println("");
        }
    } 
    
    public static void displayArray(long[] arr){
        for(long element: arr){
            System.out.print(element);
            if(element != arr[arr.length-1])
                System.out.print(" ");
            else
                System.out.println("");
        }
    } 
    
    public static double analyzeArrayDistribution(int[] arr, double median){
        int distNum = 0;
        
        for(int i = 0; i < (arr.length/2); ++i){
            if (Double.compare((double)arr[i],median) > 0){
                distNum++;
        }
      }
      return ((double)distNum / (double)arr.length);
    }
    
    public static double arrMedian(int[] arr){
        int size = arr.length;
        insertionSort(arr);
        
        if (size % 2 != 0) 
            return (double)arr[size / 2]; 
      
        return (double)(arr[(size - 1) / 2] + arr[size / 2]) / 2.0; 
    }
    
    public static void insertionSort(int[] arr){
        int n = arr.length; 
        for (int i = 1; i < n; ++i) { 
            int key = arr[i]; 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        } 
    }
    
    public static void quickSort(int[] arr, int low, int high){
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            quickSort(arr, low, pi-1); 
            quickSort(arr, pi+1, high); 
        } 
    }
    
    public static int partition(int[] data, int low, int high){
       int pivot = data[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (data[j] < pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = data[i]; 
                data[i] = data[j]; 
                data[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = data[i+1]; 
        data[i+1] = data[high]; 
        data[high] = temp; 
  
        return i+1; 
    }
    
    public static void merge(int[] arr, int l, int m, int r){
                // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    }
    
    public static void mergeSort(int[] arr, int l, int r){
        if (l < r) 
        { 
            // Find the middle point 
            int m = (l+r)/2; 
  
            // Sort first and second halves 
            mergeSort(arr, l, m); 
            mergeSort(arr , m+1, r); 
  
            // Merge the sorted halves 
            merge(arr, l, m, r); 
        } 
    }
}
