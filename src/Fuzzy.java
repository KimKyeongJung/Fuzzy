import java.util.Scanner;

public class Fuzzy {
   
   static double input_water; //입력값을 담을 변수
   static double input_laundry; //입력값을 담을 변수
   
   static double Water1(double x) { //A1
      double t1 = 0;
      if((x >= 0) && (x <= 53)) {
         t1 = 1;
      }
      else if((x > 53) && (x <= 60)) {
         t1 = (-(x-60)/(60-53))+1;
      }
      return t1;
   }
   
   static double Water2(double x) { //A2
      double t2 = 0;
      if((x > 53) && (x <= 60)) {
         t2 = ((x-60)/(60-53))+1;
      }
      else if((x > 60) && (x <= 81)) {
         t2 = 1;
      }
      else if((x > 81) && (x <= 88)) {
         t2 = (-(x-81)/(88-81))+1; 
      }
      return t2;
   }
   
   static double Water3(double x) { //A3
      double t3 = 0;
      if((x > 81) && (x <= 88)) {
         t3 = ((x-88)/(88-81))+1;
      }
      else if((x > 88) && (x <= 110)) {
         t3 = 1;
      }
      return t3;
   }

   static double Laundry1(double x) { //B1
      double h1 = 0;
      if((x > 0) && (x <= 3)) {
         h1 = 1;
      }
      else if((x > 3) && (x <= 4)) {
         h1 = (-(x-3)/(4-3))+1;
      }
      return h1;
   }
   
   static double Laundry2(double x) { //B2
      double h2 = 0;
      if((x > 3) && (x <= 4)) {
         h2 = ((x-4)/(4-3))+1;
      }
      else if((x > 4) && (x <= 7)) {
         h2 = 1;
      }
      else if((x > 7) && (x <= 8)) {
         h2 = (-(x-7)/(8-7))+1; 
      }
      return h2;
   }
   
   static double Laundry3(double x) { //B3
      double h3 = 0;
      if((x > 7) && (x <= 8)) {
         h3 = ((x-8)/(8-7))+1;
      }
      else if((x > 8) && (x <= 10)) {
         h3 = 1;
      }
      return h3;
   }
 
   
   static double MIN(double a, double b) { //최솟값 구하기(and)
      double min = 0;
      if(a<b) {
         min = a;
      }
      else {
         min = b;
      }
      return min;
   }
   
   static void Mamdani() { //맘다니 방법
      double m[] = new double[9];
      String r[] = new String[9];
      double max = 0;
      
      m[0] = MIN(Water1(input_water),Laundry1(input_laundry)); //규칙1 -> C4
      m[1] = MIN(Water1(input_water),Laundry2(input_laundry)); //규칙2 -> C2
      m[2] = MIN(Water1(input_water),Laundry3(input_laundry)); //규칙3 -> C1
      m[3] = MIN(Water2(input_water),Laundry1(input_laundry)); //규칙4 -> C3
      m[4] = MIN(Water2(input_water),Laundry2(input_laundry)); //규칙5 -> C4
      m[5] = MIN(Water2(input_water),Laundry3(input_laundry)); //규칙6 -> C2
      m[6] = MIN(Water3(input_water),Laundry1(input_laundry)); //규칙7 -> C3
      m[7] = MIN(Water3(input_water),Laundry2(input_laundry)); //규칙8 -> C3
      m[8] = MIN(Water3(input_water),Laundry3(input_laundry)); //규칙9 -> C4
      
      r[0] = "정상! (세탁율 : 100%)\n";      		//규칙1 : A1 and B1 then C4
      r[1] = "비정상! 물 부족 (세탁율 : 50%)\n";		//규칙2 : A1 and B2 then C2
      r[2] = "비정상! 물 매우 부족 (세탁율 : 30%)\n";		//규칙3 : A1 and B3 then C1
      r[3] = "비정상! 물 많음 (세탁율 : 70%)\n";  		//규칙4 : A2 and B1 then C3
      r[4] = "정상! (세탁율 : 100%)\n";   			//규칙5 : A2 and B2 then C4
      r[5] = "비정상! 물 부족 (세탁율 : 50%)\n";   		//규칙6 : A2 and B3 then C2
      r[6] = "비정상! 물 매우 많음 (세탁율 : 70%)\n"; 	//규칙7 : A3 and B1 then C3
      r[7] = "비정상! 물 많음 (세탁율 : 70%)\n";   		//규칙8 : A3 and B2 then C3
      r[8] = "정상! (세탁율 : 100%)\n";   			//규칙9 : A3 and B3 then C4
      
      int j = 0; //최댓값의 인덱스
      
      for(int i=0;i<m.length;i++) { //규칙들의 값 중에서 최댓값 구하기
         if(max < m[i]) {
            max = m[i];
            j = i;
         }
      }
      
      for(int i=0;i<m.length;i++) {
    	  System.out.println("규칙" + (i+1) + " : " + m[i]);
      }
      
      System.out.println("\n ▼ ▼ ▼\n ▼ ▼ ▼\n");
      System.out.println("규칙" + (j+1) + " : " + r[j]);
   }
   
   static void Sugeno() { //스게노 추론 방법
      double f[] = new double[9];
      int tmp[] = {100, 50, 30, 70, 100, 50, 70, 70, 100};
      
      f[0] = MIN(Water1(input_water),Laundry1(input_laundry));
      f[1] = MIN(Water1(input_water),Laundry2(input_laundry));
      f[2] = MIN(Water1(input_water),Laundry3(input_laundry));
      f[3] = MIN(Water2(input_water),Laundry1(input_laundry));
      f[4] = MIN(Water2(input_water),Laundry2(input_laundry));
      f[5] = MIN(Water2(input_water),Laundry3(input_laundry));
      f[6] = MIN(Water3(input_water),Laundry1(input_laundry));
      f[7] = MIN(Water3(input_water),Laundry2(input_laundry));
      f[8] = MIN(Water3(input_water),Laundry3(input_laundry));
      
      double sum = 0;
      double sum2 = 0;
      
      for(int i=0;i<f.length;i++) { //(퍼지함수 결과값 * 소속값)들의 합
         sum += f[i]*tmp[i];
      }
      
      for(int j=0;j<f.length;j++) { //퍼지함수 결과값들의 합
         sum2 += f[j];
      }
      
      System.out.println("\n ▼ ▼ ▼\n ▼ ▼ ▼\n");
      System.out.println("세탁율 : " + (int)(sum/sum2) + "%\n"); //무게중심
      
   }

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      Scanner sc = new Scanner(System.in);
      while(true) {
         System.out.print("맘다니 추론 방법(1 입력), 스게노 추론 방법(2 입력), 종료(3 입력) : ");
         int input = sc.nextInt();
         if(input == 1) {
            System.out.println("\n▶ 물의 양  : 0 ~ 110L, 빨랫감의 양 : 0 ~ 10kg ◀\n");
            System.out.print("물의 양 입력(L) : ");
            input_water = sc.nextDouble();
            System.out.print("빨랫감 양 입력(kg) : ");
            input_laundry = sc.nextDouble();
            
            Mamdani(); //맘다니  방법
         }
         else if(input == 2) {
        	 System.out.println("\n▶ 물의 양  : 0 ~ 110L, 빨랫감의 양 : 0 ~ 10kg ◀\n");
             System.out.print("물의 양 입력(L) : ");
             input_water = sc.nextDouble();
             System.out.print("빨랫감 양 입력(kg) : ");
             input_laundry = sc.nextDouble();
            
            Sugeno(); //스게노 방법
         }
         else if(input == 3) {
            System.out.print("\n★ 종료 ★");
            break;
         }
      }
      sc.close();
   }
}