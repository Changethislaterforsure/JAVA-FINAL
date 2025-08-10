## Directory structure
---
This project uses a modular structure so that key areas remain seperated, allowing them to be worked on seperately to create a clear and focused workflow.

### src
The src folder contains all of my source code (duh). It is broken up into other folders to keep the different aspects of my source code seperate like ow my models folder contains all of my model classes that deal with the real world items of my gym such as users and merchandise while my ui folder contains my menu's. 

### resources
The resources folder is meant to contain files used by the program, in this case my txt file that it will write to. 

### sql
This folder contains the schema that will populate my database.

### docs
This folder contains all of my documentation, it currently contains the development guide and the user guide but will contain my graphs and diagrams in the future.


## Build process
---
The build process started very minimal, I really was not 100% sure where I wanted to start. So I started by building a base, I added my postgres jar file, set up my database via homebrew terminal then built DBconnection and a very simple Main to connect to it that just printed a message confirming my program connected to the database. I created my user and admin classes then realized that there was no actual way to test them with my extremely basic main and so decided to start scaling up, creating more robust classes, their related DAO's and menu's then sprucing up Main to match them. Once I had a more complicated system going I moved to password hashing, connected menu's to the Login menu.  This is where the great oopsy began, I had begun making DAO's and menu's on different days with sort of different attitudes and design goals which led to variables not matching, variables being created then never used, things in the code not going by the name given to them in the database schema such as merchandise and gym_merch. This took time and tears to fix before I finished with my service files that help bridge my classes and DAO's. 


## How to set up database
---
I use homebrew for my database, I open the terminal on my mac and since I already have postgresql I enter 'brew services start postgresql' which starts the service. I then type in 'psql postgres' to access the interactive terminal, here you can set up a specific user but I just use the default user with no password. I then type 'CREATE DATABASE gymdb;' which creates my database, from here I can give user permissions to my database but since I am using the default user I dont do this step. Next I plug in to the database I just created with 'psql -U keyin -d gymdb' which opens a terminal specific to my database and I run my schema in it with '\i sql/schema.sql'. Here I just leave this terminal open and running, from here you can seed some data for testing but I have not done this.


## Cloning from github
---
To clone the project from github I open my VScode app, open my file named FINAL SPRINT and then open my terminal, entering the command git clone followed by the github url. To run the project you would then ensure you are in the projects root and enter: javac -cp "lib/*" ...
java -cp "out:lib/*" ui.Main      which compiles the project ensuring to include the drivers in my lib folder then runs. 