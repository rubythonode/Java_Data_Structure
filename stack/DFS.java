import javafx.geometry.Pos;

import java.util.*;

public class DFS {

  private int Number; // 항의 갯수
  private boolean EmptyNumber; // 비어있는지 확인
  private String[] Context; // 확인 내용
  public int find = 0;
  public int[][] Edge;
  public boolean[] Track;    // 갔다 왔는지 확인
  public int[] Route = {0,1,2,3,4,5,6,7,8,9};
  public char[] Code = {'A','B','C','D','E','F','G','H','I','J'}; // 확인 코드
  public Stack(){
      this.Number = 0;
      this.EmptyNumber = false;
      this.Context = new String[0];
      this.Track = new boolean[10];
      for(int i=0;i<10;i++)this.Track[i] = false;
      Edge = new int[10][];
      for(int i=0;i<10;i++){
          Edge[i] = new int[10];
      }
      Edge = new int[][]{{0, 1, 0, 0, 0, 0, 1, 0, 0, 0}, // A
              {0, 0, 1, 0, 1, 0, 0, 0, 0, 0}, // B
              {0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, // C
              {0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, // D
              {0, 0, 0, 0, 0, 1, 1, 0, 0, 0}, // E
              {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // F
              {0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, // G
              {0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // H
              {0, 0, 1, 0, 0, 0, 0, 1, 0, 0}, // I
              {0, 0, 0, 1, 0, 0, 0, 0, 1, 0}};// J
  }

  // 삽입 함수
  public void Insert(int Position, String Data){
        // 삽입 불가능한 위치에 삽입
       if((Position>(this.Number + 2)) || (Position<1)){
            System.out.println("입력할 수 없습니다.");
        }
        // 맨 처음에 추가할 경우
        // 아무것도 존재하지 않는 경우
        else if(Position == 1 && Number ==0){
           this.EmptyNumber = true;
            this.Number++;
            this.Context = new String[this.Number];
            this.Context[0] = Data;
        }
        // 맨 처음 위치에 있지만, 이미 존재하고 있는 값이 있는 경우
        else if((Position == 1) && (Number>0)){
            String[] Temp = new String[this.Number];
            for (int i = 0; i < this.Number; i++)
                Temp[i] = this.Context[i];
            this.Number++;
            this.Context = new String[this.Number];
            this.Context[0] = Data;
            for(int i=1;i<this.Number;i++) {
                this.Context[i] = "";
                this.Context[i] = Temp[i - 1];
            }

        }
        // 맨뒤에 Data를 넣을 경우
        else if(Position == this.Number+1){
            this.Number++;
            String[] Temp = new String[this.Number];
            for(int i=0;i<(this.Number)-1;i++)
                Temp[i] = this.Context[i];
                Temp[Number-1] = Data;
            this.Context = new String[this.Number];
            for(int i=0;i<this.Number;i++) {
                this.Context[i] = "";
                this.Context[i] = Temp[i];
            }
        }
        else{ // 중간에 넣는 경우
            // 포인터가 없기 떄문에 동적 배열을 사용한다.
            String[] left = new String[Position];
            String[] right = new String[this.Number - (Position-1)];
            for(int i=0;i<Position-1;i++){
                left[i] = this.Context[i];
            }
            left[Position-1] = Data;
            for(int i=Position-1;i<this.Number;i++){
                right[i-(Position-1)] = this.Context[i];
            }
            this.Number++;
            this.Context = new String[this.Number];
            for(int i=0;i<this.Number;i++){
                if(i<Position){
                    this.Context[i] = "";
                    this.Context[i] = left[i];
                }else {
                    this.Context[i] = "";
                    this.Context[i] = right[i-Position];
                }
            }

        }

  }
  // 삭제하는 함수
  public void Delete(int Position){
      // 삭제 불가능한 위치에 삽입
      if(this.Number == 0){
          System.out.println("삭제할 것이 없습니다.");
      }
      else if((Position>(this.Number + 2)) || (Position<1)){
          System.out.println("삭제할 수 없습니다.");
      }
      // 맨 처음 삭제
      else if(Position == 1 && Number ==1){
          this.Number = 0;
          this.EmptyNumber = false;
          this.Context = new String[0];
      }
      // 맨 처음 위치에 있지만, 이미 존재하고 있는 값이 있는 경우
      else if((Position == 1) && (Number>1)){
          Number--;
          String[] Temp = new String[this.Number];
          for (int i = 0; i < this.Number; i++)
              Temp[i] = this.Context[i+1];
          this.Context = new String[Number];
          for(int i=0;i<this.Number;i++) {
              this.Context[i] = "";
              this.Context[i] = Temp[i];
          }

      }
      // 맨 마지막 삭제
      else if(Position == this.Number+1) {
            this.Number--;
            String[] Temp = new String[this.Number];
            for(int i=0;i<Number;i++){
                Temp[i] = this.Context[i];
            }
            this.Context = new String[this.Number];
            for(int i=0;i<Number;i++){
                this.Context[i] = Temp[i];
            }

      } // 중간에 삭제하는 경우
      else{

          // 포인터가 없기 떄문에 동적 배열을 사용한다.
          String[] left = new String[(Position-1)];
          String[] right = new String[this.Number - (Position-1)];
          for(int i=0;i<Position-1;i++){
              left[i] = this.Context[i];
          }
          for(int i=Position-1;i<this.Number;i++){
              right[i-(Position-1)] = this.Context[i];
          }
          this.Number--;
          this.Context = new String[this.Number];
          for(int i=0;i<this.Number;i++){
              if(i<(Position-1)){
                  this.Context[i] = "";
                  this.Context[i] = left[i];
              }else {
                  this.Context[i] = "";
                  this.Context[i] = right[i-(Position-1)];
              }
          }
      }
  }
  // 해당 위치를 탐색하는 함수
  public String Retrieve(int Position){
      if(this.Number == 0){
          return "데이터가 없습니다.";

      }
      if(Position > this.Number)
          return "해당 위치에 Data가 없다.";
        else {
//          System.out.println(Position + ": " + this.Context[Position-1]);
          return this.Context[Position-1];
      }
  }
  // 다시 생성
  public void Create(){
      this.Number = 0;
      this.EmptyNumber = false;
      this.Context = new String[0];
  }
  // 리스트 자체를 삭제
  public void Destroy(){
     this.Number = 0;
     this.EmptyNumber = false;
     this.Context = new String[0];
  }
  // 비어있는지 확인
  public void Isempty(){
      if(this.EmptyNumber == false){
          System.out.println("리스트가 비어있습니다.");
      }else{
          System.out.println("리스트가 비어있지 않습니다.");
      }
  }
  // 리스트의 길이
  public int Length(){
//        System.out.println("길이 :" +  this.Number);
        return this.Number;

  }
  // 모든 항 확인
   public void AllRetrieve(){
      for(int i=0;i<this.Number;i++)
          System.out.println((i+1)+":" + this.Context[i]);
   }


   /* 스택 전용 함수 */
   // 삽입
   public void Push(String Data){
        this.Insert(1,Data);
   }
   //삭제
   public void Pop(){
        this.Delete(1);
   }
   //검색
   public String GetTop(){
       String result = this.Retrieve(1);
       return result;
   }
   // 초기화\
    public void Init(){
      this.Create();
    }
   // 파괴
    public void Stack_Destroy(){
        this.Destroy();
    }

    // Depth First Search
   public void DFS(Stack stack,int i,int End){
        if(stack.Track[i] == false) {
            stack.Push(String.valueOf(stack.Route[i])); // Stack에 집어넣음
            stack.Track[i] = true; // 해당 방문을 했다고 표시
        }
        // 목적지와 일치했을 경우 탐색 종료
        if(Integer.parseInt(stack.GetTop())==End){
            find = 1;
        }

        // 일치하는것이 끝나지 않았을 경우
        if(find == 0) {
            for (int j = 0; j < 10; j++) {
                if (stack.Edge[i][j] == 1) {
                    if (stack.Track[j] == false) {
                        stack.DFS(stack, j, End);
                    }
                }
                if(find == 1){
                    break;
                }
                if (j == 9) {
                        stack.Pop();
                        if(stack.Length() == 0) // 경로를 탐색해서 나오는 경로가 없을 경우 탐색 종료
                        { find = 1;}
                        if(find == 0) {
                            int Start = Integer.parseInt(stack.GetTop());
                            stack.DFS(stack, Start, End);
                        }
                }
            }
        }

     }


  public static void main(String[] args){
    int Temp = 0;
    DFS stack;
    int Start=0, End=0;
    String Data_Start, Data_End;
    Scanner scanner = new Scanner(System.in);

    while(true){
        stack = new Stack();
        System.out.println("해당 경로를 갈 수 있는지 탐색합니다.");
        System.out.print("시작점 : ");
        Data_Start = scanner.nextLine();
        System.out.print("종료점 : ");
        Data_End = scanner.nextLine();
        for(int i=0;i<10;i++){
            if(Data_Start.charAt(0)==stack.Code[i]){
               Start = i;
                break;
            }
        }
        for(int i=0;i<10;i++){
            if(Data_End.charAt(0)==stack.Code[i]){
                End = i;
                break;
            }
        }

        stack.DFS(stack,Start,End); // 깊이 우선 탐색 시작
        stack.find = 0;
        int length = stack.Length();
        if(length == 0){ // 찾는 경로가 없을 경우
            System.out.println("해당 경로가 없습니다.");
        }else { // 찾는 경로가 있는 경우
            for (int i = 0; i < length; i++) {
                System.out.println("경로" + (i + 1) + " : " + stack.Code[Integer.parseInt(stack.GetTop())]);
                stack.Pop();
            }
        }
        stack.Stack_Destroy();
        System.out.println("종료입니다.");
    }

  }


}
