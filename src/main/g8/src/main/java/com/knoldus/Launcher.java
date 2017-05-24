package com.knoldus;

import com.knoldus.utils.Constants;
import org.apache.hadoop.fs.FileSystem;

/**
 * Created by sangeeta on 14/4/17.
 */
public class Launcher {

    /**
     * This marks the starting point of example
     *
     * Prerequisites :
     *              1) Hadoop setup must be done in advance
     *              2) Hadoop must be up and running
     *              3) provide core-site.xml path as per your setup
     *              4) provide hdfs-site.xml path as per your setup
     *              5) provide hdfsStorePath as per your hadoop storage
     */
    public static void main(String args[]) {
        // Sample input file that will be read from local and will be stored in hdfs
        String sourcePath = "./src/main/resources/sample_input.txt";

        // Path on hdfs where the file will be stored
        String hdfsStorePath = "hdfs://localhost:54310/user/hduser/input-files/filesystem-api-example-file.txt";

        // Path to the output file that will be created from records that will be read from HDFS
        String localSystemPath = "./target/copiedToLocal.txt";
        String coreSitePath = "/home/hduser/hadoop/hadoop-2.7.3/etc/hadoop/core-site.xml";
        String hdfsSitePath = "/home/hduser/hadoop/hadoop-2.7.3/etc/hadoop/hdfs-site.xml";
        HadoopHandler hadoopHandler = new HadoopHandler();

        // Configuring Filesystem
        FileSystem fileSystem = hadoopHandler.configureFilesystem(coreSitePath, hdfsSitePath);

        // Writing to Hadoop
        String status = hadoopHandler.writeToHDFS(fileSystem, sourcePath, hdfsStorePath);
        if(status == Constants.SUCCESS) {
            System.out.println("Written to HDFS successfully");
        } else {
            System.out.println("Failed To Write to HDFS");
        }

        // Reading from Hadoop
        String readStatus = hadoopHandler.readFileFromHdfs(fileSystem, hdfsStorePath, localSystemPath);
        if(readStatus == Constants.SUCCESS) {
            System.out.println("Read from HDFS is successful");
        } else {
            System.out.println("Failed To read from HDFS");
        }

        // Close Filesystem
        hadoopHandler.closeFileSystem(fileSystem);

    }
}
