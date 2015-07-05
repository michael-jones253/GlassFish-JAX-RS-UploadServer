# GlassFish-JAX-RS-UploadServer
Restful file upload service hosted in GlassFish web server. Java JAX-RS, Maven.

## Functionality so far
Note: this project is on hold at the moment, I have become busy with other things, but intend to return to it.

Just started, currently can send files to this server using curl. The server reads the octet stream, but other than printing out how many bytes it read, does nothing.

  curl -X PUT http://localhost:8080/glassfish-jax-rs-UploadServer/webresources/devnull/hello.txt -T hello.txt 

## Aim of this project
My https://github.com/michael-jones253/Hello-Hadoop-netbeans-OS-X repository does some client side REST API programming using the popular Jersey JAX-RS API, so I thought I would try a server side file transfer project.

I aim to produce a re-useable upload package which can be configured for different filesystem URLs and interface with an abstract filesystem mount e.g write files to some local sandboxed directory or send them to a Hadoop HDFS.

Currently the only concrete implementation of the abstract file system mount is for the DevNullMount that does not put the octet stream anywhere. It is just a dummy implementation for showing how it can be set up to send anywhere. Hence the "curl -X PUT" example above has "devnull" at the start of the file URI.

## Getting Started with the GlassFish webserver in netbeans
In case this helps any newbies such as myself, these are the steps I went through:

### Install Java EE to get GlassFish
First install the plugin for java EE base - this has support for GlassFish and tomcat server.

tools -> plugins -> search

following support also gets installed:
<pre><code>
EE installed:
GlassFish Server 3 - Common
HTML5 Kit
ICEfaces
Java EE Base
JSF
Oracle Cloud
PrimeFaces REST HTML5 Client
RichFaces
Spring Web MVC
Struts 
Provides baselevel support for Java EE, i.e. web application project and basic wizards, plus support for deployment, debugging and profiling web applications on GlassFish, WebLogic, Tomcat and JBoss. Support for additional Java EE technologies such as Java Persistence, JSF and JSF component suites, REST and SOAP web services, EJB or EAR projects needs to be installed and enabled separately, as well as support for more web frameworks such as Spring or Hibernate.
</code></pre>

### Installing GlassFish
Netbeans restarts after the EE plugin install procedure above.
Only then do we see a Servers node under the Services tab in the left panel.

Right click Servers -> Add Server

A list of potential Web Servers appears. Glassfish is the easiest to add
because there is no need to exit IDE to download. Tomcat will require a
separate download, then go back to the add server dialog.

### Making a web service
Web services can be generated from databases.

Right click  Databases service -> New Connection

Now can make a netbeans project.
Maven -> Web Application -> Name and location dialog -> Choose web server e.g. Glassfish.

Right click Netbeans project -> New ->Restful web service from database.

OR -> Web Service, go back - then choose as above.

To deploy to glassfish (chosen earlier) just RUN!

To make a web service from a design pattern (like this project).
Right click Netbeans project  ->New ->Restful Web Service from Patterns.

### Fix project up so it doesn't waste time deploying every time we save a source file
Project Properties -> unselect "Deploy on Save".
Then to ensure deployment of a properly built project:

select -> Always perform build before running application

To get the URIs of the new service for testing in browser or with CURL

Open up the RESTful web service node and right click on a service
 -> Test resource URI.

Then grab the URI which includes "webresources" in the path. So far I have been unable to alter this path - changing the Context Root in the glassfish-web.xml didn't help.

### GitHub project setup with netbeans

In github choose "setup in desktop"

See my hello world for setting up Xcode, first step is same for netbeans.

Team -> Commit (make sure to highlight project and not a sub-tree otherwise complete commit doesn't happen).

Team -> Remote-> Push to Upstream.
I had problems when I selected create a remote tracking branch - so I did not do this.

Despite this the Netbeans console said:
git branch --set-upstream-to origin/master master
Branch master set to track origin/master
Enter username and password and tick save password.

Choose the radio button which says "Specify Git Repository Location"
which has the username and password (will be filled in if a previous project has already been pushed to the github profile).
Next -> select Master

Team -> Remote -> Push

Video
https://netbeans.org/kb/docs/ide/github_nb_screencast.html
