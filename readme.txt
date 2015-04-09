This repo is configured to use Glassfish and MySQL,
and you have to set up it for this program to run.

MySQL.
Install MySQL, use root/root login and password.
Create new database with name "webexpensemanager"

Glassfish.
Install and configure glassfish to run this war file.
Configure domain.
Add new connection pool (for example, MySQLConnectionPool).
Select MySQL as driver.
Select javax.sql.DataSource as resource type.
Enter fields: user, password (root/root), server (for example, localhost),
database name (webexpensemanager).

Done.