# copy the file
    hdfs dfs -copyFromLocal src dest

# compiling 
    javac -cp $(hadoop classpath) -d build src/*

# making jar 
    jar cvf WordCount.jar -C build .

# running job   
    hadoop jar WordCount.jar WordCountDriver srcInput destOutput
