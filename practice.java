
import java.sql.*;
import java.util.Scanner;

public class practice{
	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			int choice;
			do{
				System.out.println("1) Insert student : ");
				System.out.println("2) Delete student : ");
				System.out.println("3) Update student : ");
				System.out.println("4) Select student : ");
				System.out.println("5) Exit : ");
				System.out.println("Enter Your choice : ");
				Scanner sc = new Scanner(System.in);
				choice = sc.nextInt();
				
					switch(choice){
						case 1 : 
							PreparedStatement p = conn.prepareStatement("insert into student values (NULL,?,?,?);");
							System.out.println("Enter Enrolment No. : ");
							int en = sc.nextInt();
							System.out.println("Enter Student Name : ");
							String name = sc.next();
							System.out.println("Enter Student City : ");
							String city = sc.next();
							p.setInt(1,en);
							p.setString(2,name);
							p.setString(3,city);
							int r =  p.executeUpdate();
							System.out.println("Inserted ..."+r);
							break;

						case 2 :
							PreparedStatement ps = conn.prepareStatement("delete from student where id = ?;");
							System.out.println("Enter you want to delete student id : ");
							int id = sc.nextInt();
							ps.setInt(1, id);
							int z = ps.executeUpdate();
							System.out.println("deleted successfull "+ z);
							break;
						
						case 3 :
							int up ;
							do {
								System.out.println("--1) you want to update enrolment no : ");
								System.out.println("---2) youu want update student name : ");
								System.out.println("-----3) you want to update student city : ") ;
								System.out.println("-------4) Exit ... : ");
								up = sc.nextInt();
								switch (up){
									case 1:
										PreparedStatement a = conn.prepareStatement("update student set enrol = ? where id = ?;");
										System.out.println("Enter id : ");
										int rid = sc.nextInt();
										System.out.println("Enter your new enrolment no : ");
										int newen = sc.nextInt();
										a.setInt(1, newen);
										a.setInt(2, rid);
										int y = a.executeUpdate();
										System.out.println("Updated .. "+y);
										break;
									
									case 2:
										PreparedStatement ab = conn.prepareStatement("update student set name = ? where  enrol = ?;");
										System.out.print("Enter your name : ");
										String newname = sc.next();
										System.out.print("Enter your Enrolment : ");
										int enro = sc.nextInt();
										ab.setString(1, newname);
										ab.setInt(2, enro);
										int zz = ab.executeUpdate();
										System.out.println("Updated .. "+zz);
										break;
									
									case 3:
										PreparedStatement abc = conn.prepareStatement("update student set city = ? where enrol = ?;");
										System.out.print(("Enter your city : "));
										String newcity = sc.next();
										System.out.println(("Enter your enrolment : "));
										int e = sc.nextInt();
										abc.setString(1, newcity);
										abc.setInt(2, e);
										int yz = abc.executeUpdate();
										System.out.println("Updated .. "+yz);
										break;
									}

							} while (up!=4);
							break;

							case 4 :
								Statement st = conn.createStatement();
								ResultSet result = st.executeQuery("select * from student;");
								while (result.next()) {
									System.out.println("id = "+ result.getInt(1)+ ", enrol = "+ result.getInt(2)+ ", name = "+ result.getString(3)+", city = "+ result.getString(4));
								}
								result.close();
						}
				
			}while(choice!=5);
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}