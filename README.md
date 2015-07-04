# GlassFish-JAX-RS-UploadServer
Restful file upload service hosted in GlassFish web server. Java JAX-RS, Maven.

## Functionality so far
Note: this project is on hold at the moment, I have become busy with other things, but intend to return to it.

Just started, currently can send files to this server using curl. The server reads the octet stream, but other than printing out how many bytes it read, does nothing.

  curl -X PUT http://localhost:8080/glassfish-jax-rs-UploadServer/webresources/fs/hello.txt -T hello.txt 

## Aim of this project
My https://github.com/michael-jones253/Hello-Hadoop-netbeans-OS-X repository does some client side REST API programming using the popular Jersey JAX-RS API, so I thought I would try a server side file transfer project.

I aim to produce a re-useable upload package which can be configured for different filesystem URLs and interface with any filesystem e.g write files to some local sandboxed directory or send them to a Hadoop HDFS.


