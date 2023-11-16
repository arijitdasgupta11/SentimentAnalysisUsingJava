# SentimentAnalysisUsingJava
#Technology Stack Used:
1. Programming Language: Java 
2. Technology: Servlet
3. Web Server: Tomcat 9
4. Database Tool: JDBC, Oracle SQL Developer
5. Library for Sentiment Analysis: Stanford CoreNLP
6. Mail Service: Javax Mail Package
7. Data Structures Used: ArrayList, Sentiment Annotated 
Tree,CoreMap
8. Database Mechanism Used: Triggers, Sequence



#Folder Structure Breakdown:
1. src: Contains the package and sub package within that actual java 
classes (.java file) (servlet, helping class).
2. build: Contains the package and sub package within that java class 
file (.class file).
3. Web-Content: Containing the home page (index.html) and WebINF (Web Information) folder.
4. Web-INF: Containing the deployment descriptor(web.xml) and lib 
folder which contains all required jar files for this application like 
Stanford library file, javax mail service jar and ojdbc jar files.



#Java Classes Usage:
1. AnalysisServlet.java: Starting point of our application where we 
retrieving user input from html form and storing it into databases.
2. DatabaseOperations.java: This java file performs all the data 
retrieval operations from connecting via JDBC to store it into a list.
3. DateChangeTracker.java: This java file checks if the system date 
changes then it will perform the Send Mail task to the subscribers 
to keep updated the subscribers.
4. SendMailNew.java: This java file performs the sending of mail to 
the subscribers.
5. SentimentAnalysis.java: This java file analyses the sentiment of 
each comment of users and also calculate a score combined with 
their rating.
6. Subscribers.java: This java file calls the SendMailNew.java class to 
send the mail with proper subject line to the subscribers.
 
