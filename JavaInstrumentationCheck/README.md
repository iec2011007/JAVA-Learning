This project deals with how to find the size of the object. 
The toughest thing which i faced was configuring the maven so that the manifest file could be properly configured. 
The above pom shows how to do the same

This project has been setup for using the premain-class way of configuring the java instrumentation agent. If you want to use that package the project

java -javaagent:target/test-jar-with-dependencies.jar -jar target/test-jar-with-dependencies.jar

But i found the following problem using it that way. 
Suppose the variable a hold an object of size 1Mb but variable b has the reference of variable a. Then on using this library you would get 4bytes which is the 
amount of memory required to hold the reference. Hence we have to write our custom logic so that we can follow the references and then add up the sizes.

Same has been done in the classmexes.jar and which could be invoked by the following command

java -javaagent:/home/local/JASPERINDIA/aman.verma/project/JavaInstrumentationCheck/src/test/resources/memory/classmexer.jar -cp target/test-jar-with-dependencies.jar dustin.examples.InstrumentSampleObjects


Apart  from these two there is one there is a class TestClass.java which has a method estimateRowSize which can be used if we want to estimate the row size.
