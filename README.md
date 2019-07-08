# download 
1. ubuntu server 14.04
2. hadoop 2.7.1


# install java 

    sudo apt install openjdk-11-jdk

    # set environment variables in .bashrc
    
    #JAVA VARIABLES
    export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
    export PATH=$PATH:$JAVA_HOME/bin



# ubuntu sepration for hadoop (OPTIONAL)
    sudo addgroup hadoop_group
    sudo adduser --ingroup hadoop_group hadoop
    sudo adduser hadoop sudo    


#ssh
    sudo apt install ssh
    
    ssh-keygen -t rsa -P ""
    cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys

    #checking
    ssh localhost


#hadoop install
    # extract hadoop
        sudo tar -xvf hadoop-2.7.1.tar.gz

    #create folder hadoop under /usr/local
        move hadoop(extract folder) to /usr/local/hadoop

        sudo mv ~/hadoop-2.7.1/*  /usr/local/hadoop/

    # change ownership to hadoop user
    sudo chown -R username(:group) pathOfFolder

    #set Hadoop variables in .bashrc
        #HADOOP VARIABLES
        export HADOOP_HOME=/usr/local/hadoop
        export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
        export HADOOP_INSTALl=$HADOOP_HOME
        export HADOOP_MAPRED_HOME=$HADOOP_HOME
        export HADOOP_COMMON_HOME=$HADOOP_HOME
        export HADOOP_HDFS_HOME=$HADOOP_HOME
        export YARN_HOME=$HADOOP_HOME
        export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native

    source .bashrc
    cd $HADOOP_HOME/etc/hadoop

# file modification  

        #hadoop-env.sh 
            export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

        #make tmp directory 
            sudo mkdir -p   /app/hadoop/tmp

        #core-site.xml
            <configuration>
                <property>
                        <name>hadoop.tmp.dir</name>
                        <value>/app/hadoop/tmp</value>
                </property>
                <property>
                        <name>fs.default.name</name>
                        <value>hdfs://localhost:54310</value>
                </property>
            </configuration>


        #mapred-site.xml (cp mapred-site.xml.template mapred-site.xml)
            
            <configuration>
                <property>
                        <name>mapred.job.tracker</name>
                        <value>localhost:54311</value>
                </property>
                <property>
                        <name>mapreduce.framework.name</name>
                        <value>yarn</value>
                </property>
            </configuration>

        #hdfs-site.xml  
            make 2 dir under /usr/local/hadoop_store/hdfs/ namenode & datanode

            <configuration>
                <property>
                        <name>dfs.replication</name>
                        <value>1</value>
                </property>
                <property>
                        <name>dfs.namenode.name.dir</name>
                        <value>file:///usr/local/hadoop_store/hdfs/namenode</value>
                </property>
                <property>
                        <name>dfs.namenode.data.dir</name>
                                <value>file:///usr/local/hadoop_store/hdfs/datanode</value>
                </property>

            </configuration>


        #yarn-site.xml
            <configuration>
                    <property>
                            <name>yarn.nodemanager.aux-services</name>
                            <value>mapreduce_shuffle</value>
                    </property>

            </configuration>



#formating hadoop file system   
    hdfs namenode -format







#hadoop command 
    hadoop version
    hdfs dfs -ls /
    hdfs dfs -

    hdfs dfs -copyFromLocal ~/localfile /hdfsfile   

    hdfs dfs -mkdir /test   
    

    #starting namenode datanode
            start-dfs.sh
    #starting mapreduce 
            start-yarn.sh

    #checking 
            jps  
        result
            nodemanager
            resourceManager
            datanode
            secondaryNamenode
            namenode
            jps

    #stoping
        stop-dfs.sh
        stop-yarn.sh        

#interface
    localhost:50070 (namenode)
    localhost:8088 (resource manger)
    localhost:50090/logs (logs)

