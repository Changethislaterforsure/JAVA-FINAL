# USER GUIDE
-----



## What this app is and how it works 
---
This is a simple menu driven program that helps a gym keep track of people, classes and merhandise including its own trainers. A user can log in as a gym member, a system admin r a trainer, from there accessing other menu's and information relevant to them; for example a trainer logging in can create new classes for them to lead or view all their currently scheduled classes. Everything you do in these menu's is saved to a postgresql database set up on your computer as detailed in the development guide. Login information as well as trainer activity is also saved to a text file so you can keep track of user activity. 


## The moving parts and how they interact
---
It is best to think of this program in 4 layers:

The top layer is UI, the stuff you can see. This includes all of the menu's as well as ui.Main which starts the app to begin with. 

The next layer is service, it is concerned with rules and logic, briding the gap between the UI layer and the database layer.

The next layer is the database layer, or DAO layer, it reads and writes information from the program to the database or vice versa. 

The bottom layer is the models, the baseline identities of the system setting a expectation of what the different aspects like membership and trainer are supposed to look like. A simple metaphor for how this works is that the models are the blueprint drawn by the architect but UI are the practices the builders must apply while constructing the architects plan. 


## Build and run
---
After copying the program from github or downloading it from some other source, open the terminal at its root and run the following:
javac -cp "lib/*" -d out src/util/*.java src/models/*.java src/dao/*.java src/service/*.java src/ui/*.java
java -cp "out:lib/*" ui.Main

If the database has been set up correctly along with the correct drivers (as the .gitignore will omit them) you will see a success message and then the main menu will open allowing you to register and login once registered. The menu layout is fairly simple and straight forward and so should not take much getting use to at all. 

## Flow
---
+------------------+          +----------------------+          +------------------+          +-------------------------+
|   MemberMenu     |          |   MembershipService  |          |   MembershipDAO  |          |  PostgreSQL (gymdb)     |
|  (UI / console)  |          | (business rules)     |          | (SQL operations) |          |  tables: memberships... |
+---------+--------+          +----------+-----------+          +---------+--------+          +------------+------------+
          |                              |                                 |                               |
          | choose "Purchase"            |                                 |                               |
          | enter type/desc/cost         |                                 |                               |
          |----------------------------->|                                 |                               |
          |                              | create Membership model         |                               |
          |                              |-------------------------------> |                               |
          |                              |                                 | INSERT INTO memberships (...) |
          |                              |                                 |------------------------------>|
          |                              |                                 |                               |
          |   success / failure          |  boolean result                 |   update rowcount             |
          |<-----------------------------|<------------------------------- |<------------------------------|
          | print message                |                                 |                               |
          v                              v                                 v                               v
   (logger writes to resources/logger.txt at key steps: start purchase, success/fail)