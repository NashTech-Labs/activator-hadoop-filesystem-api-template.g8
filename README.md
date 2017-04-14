# Activator-Hadoop-Filesystem-Api

This is an activator template that is designed to demonstrate how you can read and write files to/from hadoop to/from your system.

## Steps to run this Project

1) Install Hadoop : One good link to do that is : [Installation Steps - Hadoop](http://www.michael-noll.com/tutorials/running-hadoop-on-ubuntu-linux-single-node-cluster/)

2) Start Hadoop by running command:
```$xslt
cd $HADOOP_HOME
./sbin/start-all.sh
```
 
3) Set variables in Launcher.java class as specified below :

 After hadoop server is running configure core-site.xml path and hdfs-site.xml path as per your hadoop setup on your local machine. Also set your storeLocation on hadoop as per your filestructure on hdfs.
 
4) Clone project and Run

```$xslt
git clone git@github.com:SangeetaGulia/activator-hadoop-filesystem-api.git
```
 
5) Run Project

```$xslt
cd activator-hadoop-filesystem-api
bin/activator "run-main com.knoldus.Launcher"
```

For any issue please raise a ticket @ [Github Issue](https://github.com/SangeetaGulia/activator-hadoop-filesystem-api/issues)