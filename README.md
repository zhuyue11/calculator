Calculator
===

General information
---
This is a calculator application.

Now only an CLI RPN calculator is implemented. And only <code>+ - * / and sqrt</code> are supported right now.
More information about RPN: https://en.wikipedia.org/wiki/Reverse_Polish_notation

How does it work?
---
When the application has started, you can enter numbers and operators separated by space like</br>
<code>1 2 +</code></br>
The calculator will calculate. Then it will print all the all un-operated numbers in input order. After the last operation, it will print</br>
<code>3</code></br>

How to run the application
---
<b>Package</b> mvn -B clean package</br>
<b>Run</b> </br>
Run for linux/MacOs <code>sh run.sh</code> </br>
Run for Window <code>bash run.sh</code> </br>
or <code>java -jar target/calculator.jar</code>
