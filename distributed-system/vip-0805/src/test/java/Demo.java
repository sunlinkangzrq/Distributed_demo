
public class Demo {
	//1、数组最大数字，返回坐标
	public static void main(String[] args) {
//		int[]  temp=new int[6];
//		temp[0]=1;
//		temp[1]=5;
//		temp[2]=12;
//		temp[3]=4;
//		temp[4]=-8;
//		temp[5]=9;
//		System.out.println(getMax(temp));
		
		int[]  temp2={2,4,6,9};
		int[]  temp3={1,4,5,8};
		System.out.println(list1Andlist2(temp2,temp3));
	}
	
	public  static int  getMax(int[]  intList) {
		if(intList!=null &&  intList.length>0) {
			int maxValue=intList[0];
			int  maxPosition=0;
			for(int i=1;i<intList.length;i++) {
				if(maxValue<intList[i]) {
					maxValue=intList[i];
					maxPosition=i;
				}
			}
			return maxPosition;
		}
		return -1;
	}
	
	//合并两个有序数组，
	public  static int[]   list1Andlist2(int[]  list1,int[] list2) {
		int list1Size=list1.length;
		int  list2Size=list2.length;
		int resultSize=list1Size+list2Size;
		int[]  resultList=new int[resultSize];
		int list1P=0;
		int list2P=0;
		int temp=0;
		while(temp<resultSize) {
			if(list1[list1P]<list2[list2P]) {
				resultList[temp]=list1[list1P];
				list1P++;
			}else {
				resultList[temp]=list2[list2P];
				list2P++;
			}
			temp++;
		}
		return resultList;
	}
	
}
