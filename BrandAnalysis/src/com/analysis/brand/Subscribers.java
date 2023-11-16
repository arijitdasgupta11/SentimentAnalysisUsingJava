package com.analysis.brand;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Subscribers {

	public synchronized static void sendMail() {
		ResultSet users = DatabaseOperations.userRegisteredBrand();

		try {
			while (users.next()) {
				String mailid = users.getString(1);
				String brandname = users.getString(2);
				ResultSet score = DatabaseOperations.scoreOfABrand(brandname);
				while (score.next()) {
					if (score.getString(1).equals("true") && score.getDouble(2)>10) {
						SendMailNew.sendMail(
								"Your subscribed brand "+brandname+" gained popularity. Current Points: " + score.getDouble(2),
								"ALERT!!!! GAINING POPULARITY", mailid, "jobrecruitersuniverse@gmail.com");
					} else if (score.getString(1).equals("false")){
						SendMailNew.sendMail(
								"Your subscribed brand "+brandname+" losing popularity. Current Points: " + score.getDouble(2),
								"ALERT!!!! LOSING POPULARITY", mailid, "jobrecruitersuniverse@gmail.com");
					}
					else
					{
						SendMailNew.sendMail(
								"Your subscribed brand "+brandname+" is still standing in the base score: " + score.getDouble(2),
								"NO CHANGE", mailid, "jobrecruitersuniverse@gmail.com");
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
