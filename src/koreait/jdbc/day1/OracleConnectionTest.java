package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
//다른 DBMS 클라이언트 프로그램과 같이 db를 사용할 수 있는 동작을 구현
// 제일 먼저 해야할 것은 '데이터베이스 연결' 이다.
public class OracleConnectionTest {

   public static void main(String[] args) {
      // 0. Connection은 인터페이스로 직접 객체를 생성하지 않고 구현 클래스가 필요
      // db에서는 db 드라이버가 접속하려는 db에 종류에 따라 알아서(프록시=대행)
      // 구현 클래스와 구현 객체를 만듭니다.
      Connection conn=null;
      
      String url="jdbc:oracle:thin:@localhost:1521:xe";
      //1. 아래 4개의 필수 연결 정보를 설정합니다.
      //접속하고자 하는 서버의 주소(포탈접속 https://www.naver.com 과 비슷한 개념)
      String driver="oracle.jdbc.driver.OracleDriver";
      //oracle.jdbc.driver는 ojdbc.jar에 포함된 패키지 이름
      //OracleDriver 는 오라클드라이버 클래스 이름
      String user="iclass";
      String password="0419";
      
      try {
         
      //2. 드라이버 클래스 객체를 메모리에 로드(올리기)하기
   //        ㄴ 연결 객체를 만들어주는 역할을 한다.
         Class.forName(driver);
               
         conn=DriverManager.getConnection(url,user,password);
   // 3. DriverManager 클래스는 연결 객체를 만든다.- 2번의 객체를 동작시킨다.
   //  이때 2번에서 만든 객체 즉 dbms에 따라 구현객체가 만들어진다.
    // 4. 3번의 결과로 오라클 db에 맞는 연결객체가 생성이 된다.
         System.out.println("연결 상태="+conn);
         if(conn!=null)
            System.out.println("오라클 데이터베이스 연결 성공 !!");
         else
            System.out.println("오라클 데이터베이스 연결 실패 !!");
         
      } catch (Exception e) {// ClassNotFoundException, SQLException 처리가 필요
          System.out.println("ClassNotFoundException = 드라이버 경로가 잘못되었습니다.");
          System.out.println("SQLException = url 또는 user 또는 password가 잘못되었습니다." );
          System.out.println("오류 메세지="+e.getMessage());
          e.printStackTrace(); //Exception 발생의 모든 원인을 cascade 형식으로 출력
      }finally {
         try {
            if(conn !=null)
              conn.close();
         } catch (Exception e) {   // SQLException 처리
         //명시적으로 자원을 close => 실행 안해도 오류는 안난다.
         }
      }

   }

}
/* API : Application Programming Interface
 *     : 서로 다른 소프트웨어 시스템 간의 연결을 위한 방식(라이브러리로 제공)
 * 
 * 라이브러리 : 자바 라이브러리와 같이 특정 기능을 제공하는 클래스들의 집합. 확장자는
 *            압축형태 .jar
 * jdbc : 자바와 dbms를 연결하는 api
 */ 