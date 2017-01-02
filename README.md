## Synopsis

Example of CRUD Web application with postgres database.
  
## Motivation  

Project for Brian James 

## Running and packing in sbt

To start this app in sbt do commands as follows:

$ sbt

\> run

To pack it in one directory with libraries (.jar) 

\> pack

## Application deployment and running

1. Copy public,conf folders and start,stop files to application root directory of remote server.
2. Use command ./mkarc to make archive app.tar.gz
3. Move file app.tar.gz to application root directory of remote server.
4. Extract file app.tar.gz on remote server: tar -xvf app.tar.gz
5. Application parameters are placed in conf/config.txt. We can change it before starting.  
6. Use commands ./start and ./stop to start and to stop application on remote server.
7. Use http://remote_server_name_or_IP:port in web browser. 