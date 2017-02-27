# TextDigester

TextDigester is a self-contained Java library that impelements several text summarization approaches relying on (and thus imporing) the following libraries:

* Freeling (v 4.0): http://nlp.cs.upc.edu/freeling/  
* GATE (v 8.3): https://gate.ac.uk/  
* Deeplearning4j (v 0.7.2): https://deeplearning4j.org/  


TextDigester is structured as a Maven project working with Java 1.8.  

In order to import text digester in your program:

* Clone and compile this Maven project  
* Download the resource folder and the property file of TextDigester from http://www.backingdata.org/textdigester/TEXTDIGESTER_RESOURCES_v_0_0_2.tar.gz  
* Modify the property file of TextDigester by specifying the local path to the resource folder of TextDigester (downloaded in the previous tar.gz file) and the local path of your GATE installation  

Before using TextDigester library methods, remember to set the path to the property file of TextDigester by means of the following code:   
```javascript
edu.upf.taln.textdigester.setting.PropertyManager.setPropertyFilePath("/home/francesco/Desktop/NLP_HACHATHON_4YFN/TextDigesterConfig.properties");
```  



