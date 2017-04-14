package com.knoldus;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

import com.knoldus.utils.*;

public class HadoopHandler {

    /**
     * This method configures the file system
     * @param coreSitePath Path to core-site.xml in hadoop
     * @param hdfsSitePath Path to hdfs-site.xml in hadoop
     * @return HadoopFileSystem instance
     */
    public FileSystem configureFilesystem(String coreSitePath, String hdfsSitePath) {
        FileSystem fileSystem = null;

        try {
            Configuration conf = new Configuration();
            Path hdfsCoreSitePath = new Path(coreSitePath);
            Path hdfsHDFSSitePath = new Path(hdfsSitePath);
            conf.addResource(hdfsCoreSitePath);
            conf.addResource(hdfsHDFSSitePath);

            fileSystem = FileSystem.get(conf);
            return fileSystem;
        } catch (Exception ex) {
            System.out.println("Error occurred while Configuring Filesystem ");
            ex.printStackTrace();
            return fileSystem;
        }
    }

    /**
     *
     * @param fileSystem refers to Hadoop FileSystem instance
     * @param sourcePath provides the sample input file which can be written to HDFS
     * @param destinationPath refers to path on hdfs where the sample input file will be written
     * @return
     */
    public String writeToHDFS(FileSystem fileSystem, String sourcePath, String destinationPath) {
        try {
            Path inputPath = new Path(sourcePath);
            Path outputPath = new Path(destinationPath);
            fileSystem.copyFromLocalFile(inputPath, outputPath);
            return Constants.SUCCESS;
        } catch (IOException ex) {
            System.out.println("Some exception occurred while writing file to hdfs");
            return Constants.FAILURE;
        }
    }


    /**
     *
     * @param fileSystem refers to Hadoop FileSystem instance
     * @param hdfsStorePath refers to path on hdfs where the sample input file is present
     * @param localSystemPath refers to a location of file on local system in which data read from hadoop file will be written
     * @return
     */
    public String readFileFromHdfs(FileSystem fileSystem, String hdfsStorePath, String localSystemPath) {
        try {
            Path hdfsPath = new Path(hdfsStorePath);
            Path localPath = new Path(localSystemPath);
            fileSystem.copyToLocalFile(hdfsPath, localPath);
            return Constants.SUCCESS;
        } catch (IOException ex) {
            System.out.println("Some exception occurred while reading file from hdfs");
            return Constants.FAILURE;
        }
    }

    /**
     *  This closes the FileSystem instance
     * @param fileSystem
     */
    public void closeFileSystem(FileSystem fileSystem) {
        try {
            fileSystem.close();
        } catch (Exception ex) {
            System.out.println("Unable to close Hadoop filesystem : " + ex);
        }
    }

}
