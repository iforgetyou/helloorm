package com.google.appengine.demos.helloorm;

public class QSort 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO 自动生成方法存根
		quicksort qs = new quicksort();
//		int data[] = {44,22,2,32,54,22,88,77,99,11};
//        int data[] = {3,2,1};
//        int data[] = {2,3,1};
        int data[] = {5,4,3,1,2};
        int[] srcData=new int[data.length];
        System.arraycopy(data,0,srcData,0,data.length);
		qs.data = data;
		qs.sort(0, qs.data.length-1);
		qs.display();
        int times = 0;
        for (int i = 0; i < qs.data.length/2+1; i++) {
            if (qs.data[i]!=srcData[i]){
                times++;
            }
        }
        System.out.println();
        System.out.println(times);
    }

}


class quicksort
{
	public int data[];
	
	private int partition(int sortArray[],int low,int hight)
	{
		int key = sortArray[low];
		
		while(low<hight)
		{
			while(low<hight && sortArray[hight]>=key)
				hight--;
			sortArray[low] = sortArray[hight];
			
			while(low<hight && sortArray[low]<=key)
				low++;
			sortArray[hight] = sortArray[low];
		}
		sortArray[low] = key;
		return low;
	}
	
	public void sort(int low,int hight)
	{
		if(low<hight)
		{
			int result = partition(data,low,hight);
			sort(low,result-1);
			sort(result+1,hight);
		}
		
	}
	
	public void display()
	{
		for(int i=0;i<data.length;i++)
		{
			System.out.print(data[i]);
			System.out.print(" ");
		}
	}
}