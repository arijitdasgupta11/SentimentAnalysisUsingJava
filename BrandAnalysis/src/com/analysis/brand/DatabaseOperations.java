package com.analysis.brand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {

	public synchronized static int brandExists(String brandname) {
		int id = -1;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "TIGER");

				String query = "SELECT BRANDID FROM BRANDS WHERE NAME=?";

				PreparedStatement pstmt = conn.prepareStatement(query);

				pstmt.setString(1, brandname);
				ResultSet records = pstmt.executeQuery();

				if (records.next()) {
					id = records.getInt(1);
					return id;
				}
				conn.close();
			} catch (SQLException e) {
				System.out.println("brandexists");
			}
		} catch (ClassNotFoundException e) {

			System.out.println("brandexists");
		}

		return id;
	}

	public synchronized static boolean getRatings(String brandname) {
		int brandid = DatabaseOperations.brandExists(brandname);

		if (brandid >= 1) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");

				try {
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott",
							"TIGER");

					String query = "SELECT COMMENTS,RATING FROM REVIEWS WHERE TRUNC(TIME)=TRUNC(CURRENT_TIMESTAMP) AND BRANDID=?";

					PreparedStatement pstmt = conn.prepareStatement(query);

					pstmt.setInt(1, brandid);
					ResultSet records = pstmt.executeQuery();

					List<String> comments = new ArrayList<>();
					List<Double> ratings = new ArrayList<>();

					while (records.next()) {
						comments.add(records.getString(1));
						ratings.add(records.getDouble(2));
					}

					if (comments.size() > 0) {

						SentimentAnalysis.analyze(comments, ratings, brandname);
						return true;
					} else {
						System.out.println("No reviews Today!");
					}
					conn.close();
				} catch (SQLException e) {
					System.out.println("getratings");
				}
			} catch (ClassNotFoundException e) {

				System.out.println("getratings");
			}
			return false;
		} else {
			if (DatabaseOperations.createBrand(brandname)) {
				System.out.println(
						"Just enlisted your brand. Will get back to you whenever we will get more reviews about it.");
				return true;
			} else {
				System.out.println("Internal Error");
			}
			return false;
		}
	}

	public synchronized static boolean createBrand(String brandname) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "TIGER");

				String query = "INSERT INTO BRANDS(NAME) VALUES(?)";

				PreparedStatement pstmt = conn.prepareStatement(query);

				pstmt.setString(1, brandname);

				int count = pstmt.executeUpdate();

				if (count > 0) {

					return true;
				}
				conn.close();
			} catch (SQLException e) {
				System.out.println("createbrand");
			}
		} catch (ClassNotFoundException e) {

			System.out.println("createbrand");
		}

		return false;
	}

	public synchronized static double getCurrentScore(int id) {
		double score = 0.0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "TIGER");

				String query = "SELECT SCORE FROM BRANDS WHERE BRANDID=?";

				PreparedStatement pstmt = conn.prepareStatement(query);

				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				rs.next();

				score = rs.getDouble(1);

				conn.close();
			} catch (SQLException e) {
				System.out.println("currentscore");
			}
		} catch (ClassNotFoundException e) {

			System.out.println("currentscore");
		}
		return score;

	}

	public synchronized static void updateCurrentScore(double score, int id) {
		double cur = DatabaseOperations.getCurrentScore(id);
		String increased = cur > score ? "false" : "true";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "TIGER");

				String query = "UPDATE BRANDS SET SCORE=?,INCREASED=? WHERE BRANDID=?";

				PreparedStatement pstmt = conn.prepareStatement(query);

				pstmt.setDouble(1, score);
				pstmt.setString(2, increased);
				pstmt.setInt(3, id);

				pstmt.executeUpdate();

				conn.close();
			} catch (SQLException e) {
				System.out.println("5");
			}
		} catch (ClassNotFoundException e) {

			System.out.println("6");
		}
	}

	public synchronized static boolean userRegister(String useremail, String brandname) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "TIGER");

				String query = "INSERT INTO SUBSCRIBERS(EMAILID,BRANDNAME) VALUES(?,?)";

				PreparedStatement pstmt = conn.prepareStatement(query);

				pstmt.setString(1, useremail);
				pstmt.setString(2, brandname);

				int count = pstmt.executeUpdate();

				if (count > 0) {
					return true;
				}
				conn.close();
			} catch (SQLException e) {
				DatabaseOperations.userUpdate(useremail, brandname);
			}
		} catch (ClassNotFoundException e) {

			System.out.println("8");
		}

		return false;

	}

	public synchronized static boolean userUpdate(String useremail, String brandname) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "TIGER");

				String query = "UPDATE TABLE SUBSCRIBERS SET BRANDNAME=? WHERE EMAILID=?";

				PreparedStatement pstmt = conn.prepareStatement(query);

				pstmt.setString(1, brandname);
				pstmt.setString(2, useremail);

				int count = pstmt.executeUpdate();

				if (count > 0) {
					return true;
				}
				conn.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		} catch (ClassNotFoundException e) {

			System.out.println("8");
		}

		return false;

	}

	public synchronized static ResultSet userRegisteredBrand() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "TIGER");

				String query = "SELECT EMAILID,BRANDNAME FROM SUBSCRIBERS";

				PreparedStatement pstmt = conn.prepareStatement(query);

				return pstmt.executeQuery(query);

			} catch (SQLException e) {
				System.out.println("9");
			}
		} catch (ClassNotFoundException e) {

			System.out.println("01");
		}

		return null;

	}

	public synchronized static ResultSet scoreOfABrand(String brandname) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "TIGER");

				String query = "SELECT INCREASED,SCORE FROM BRANDS WHERE NAME=?";

				PreparedStatement pstmt = conn.prepareStatement(query);
				System.out.println(brandname);
				pstmt.setString(1, brandname);

				return pstmt.executeQuery();

			} catch (SQLException e) {
				System.out.println(e);
			}
		} catch (ClassNotFoundException e) {

			System.out.println("12");
		}

		return null;

	}

}
