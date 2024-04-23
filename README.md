# Resume

I am a Software QA Automation Engineer with experience primarily in Functional Testing for Web Browsers and Mobile Applications.

This guide is intended for beginners or junior professionals who aspire to develop a comprehensive Framework for conducting Functional Testing. There are numerous tools and frameworks available for Testing, and this guide focuses on 
those based on widely-used technologies such as Selenium and Cucumber. It aims to assist in common QA Automation tasks, such as implementing a Testing Framework for Regression Testing, Smoke Testing, Sanity Testing, etc.

# Purpose of the Framework.
The purpose of this guide is to provide a well-structured and documented approach for building an Automated Testing Framework from scratch. Additionally, it will include links to the official documentation of the tools we use, enabling 
users to familiarize themselves with the official resources. Furthermore, we will address common challenges and difficulties that may arise when following this guide. Most importantly, this guide will provide a foundational framework 
for users to begin implementing their test cases, regression suites, and more.

# 
### java-core-web-automation-bdd
# 

To set up and use the Testing Framework, ensure that you have the following prerequisites installed:
1. Java Development Kit (JDK) (on my case I am ussing 13.0.14 Azul Zulu)        // I recommend you to use the last version. If you want this old versión you must register and create an account on Oracle official Website
2. Maven (on my case I am ussing 3.9.6)                                        // I recommend you to use the last version.
3. IntelliJ IDEA                                                              //  I recommend you to use the last version.
#

### About the Framework.
We will focused this Framework on a Methology called BDD (they exists others like TDD, ATDD, etc). 

**BDD (Behavior-Driven Development)** is a software development methodology that encourages collaboration between developers, QA engineers, and business stakeholders. Can be implemented on **all Development Software & Software Development Life Cycle
(SDLC)**. 

**On Testing** it emphasizes writing test cases in a **human-readable language** that can be understood by all parties involved in the development process. **BDD focuses on defining the behavior of a system from the end user's perspective.**
**One of the most common well known tool for archiving this is Cucumber and moreover Gherkin** because **we can write test cases in a human-readable language that can be understood by all humans.**

More specifically Cucumber allows the creation of 
executable specifications written in plain text using the Gherkin syntax. Gherkin is a simple, structured language that uses keywords like Given, When, Then, And, and But to describe the behavior of a system in a way that is understandable 
by non-technical stakeholders. Cucumber then translates these specifications into automated tests that can be executed by QA engineers.

Also a key thing about the Framework is that uses a design patter called POM (Page Object Model).
Page Object Model is a design pattern (as we said) used in test automation to enhance test maintenance and readability. 
It involves creating separate classes for each web Page** or component of a Web Application, encapsulating the interactions with those elements within their respective classes. These page classes expose methods that represent the actions 
that can be performed on the page, making the test scripts more readable and maintainable. So, Selenium WebDriver it is completed with POM by structuring the test code in a way that separates the page-specific actions, the page objects from
the test case. This separation of concerns improves the maintainability and scalability of the test automation codebase.

# Tools
Selenium WebDriver: This tool is employed for controlling web browsers and automating web application testing. Selenium WebDriver allows testers to interact with web elements, simulate user actions, and validate application behavior 
across different browsers. https://www.selenium.dev/documentation/webdriver/

Before proceeding, it's important to consider the version of Selenium you're using. In this project, we use version 4 (specifically version 4.19.1), although you can use any version within the 4 series. However, you should be cautious because 
each version of Selenium is compatible with specific versions of web browsers.
[Selenium on Maven](https://www.selenium.dev/documentation/webdriver/getting_started/)

Cucumber and Gherkin: Cucumber is utilized as the BDD framework, while Gherkin serves as the language for defining test scenarios in a human-readable format. Cucumber enables collaboration between technical and non-technical stakeholders 
by translating Gherkin scenarios into executable test scripts. https://cucumber.io/docs/installation/java/

Regarding Cucumber, it's necessary to mention that to implement Cucumber, we'll need Cucumber-JVM, which is required for using Cucumber with Java. We are using Cucumber 7 (more specifically 7.16.1) Additionally, in our case. 
Additionally, in our case, we use JUnit 5, and here we'll see a significant difference between implementing Cucumber with JUnit 4 and with JUnit 5. Specifically, for JUnit 4, it may be simpler since it has been around for 
longer. However, this guide aims to use the latest and most recent versions of each tool.
[Cucumber7 on Java](https://cucumber.io/docs/installation/java/)
[Cucumber7 on Junit5](https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-junit-platform-engine)

Allure Report: Allure Reports is a tool for generating interactive and detailed test reports. It is basicaly the most powerfull open-source reporting tool. Now a days it is been using a lot. Obviously it can be integrated with Cucumber 
to provide comprehensive reports with visual representations of test results, enhancing the readability and analysis of Cucumber test executions. Also, it supports the implementation with Junit5, JUnit4, TestNG, Cypress and other Testing tools.

[Allure on Junit5 & CucumberJVM](https://allurereport.org/docs/cucumberjvm/)
[Starting Allure Project Generator](https://allurereport.org/start/). For us this can be usefull to see what dependencies we need and how we should implement the build of maven for generating and serve de Allure Report.
If you use this tool. Remeber that we are using Java Language, Cucumber Framework (Do not select Junit) & Junit-platform for the runner option

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Now we are going to discuss something that I always find interesting when starting from scratch with any framework or development, which is how the project directories will be structured. 
We will also discuss the dependencies in the pom.xml required for implementing the framework and the most important details of the pom. 
Finally, we will briefly discuss the purpose of the files that I consider most important and some points of interest about these files.

# 1. Project Structure

```
java-core-web-automation-bdd
├── .allure
├── .gitignore
├── .idea
├── driver                                  // This directory holds browser drivers. If we do not specified (on the path configuration), Selenium will automatically download the required driver.
├── output                                  // This directory saved the logs of the execution & the Logs that belong to the Drivers
├── pom.xml                                 // Configuration file for Maven projets. Here we add the repositories, set dependencies, build configurations, set plugins, etc
└── src
    ├── main
    │   ├── java
    │   │   └── org.framework.selenium
    │   │       └── Main.java              
    │   └── resources
    └── test
        ├── java
        │   ├── config
        │   │   ├── Log.java                // Class for logging configuration. This Logging configuration will print on the Terminal an output & generate and log.file on output directory
        │   │   ├── WebActions.java         // Class for common web actions (e.g., click, sendkeys, waiats, etc)
        │   │   └── WebDriver.java          // Class for initializing and managing WebDriver instance
        │   ├── pom
        │   │   ├── HomePage.java           // Page Object Model class for homepage. This may be seen as the Page of the POM(Page-Object-Model) design pattern.
        │   │   └── HomePageObjects.java    // Page Object Model class containing web elements for homepage. This may be seen as the Object of the POM(Page-Object-Model) design pattern.
        │   ├── runners
        │   │   ├── HookStep.java           // Class defining Cucumber hooks (e.g., cucumber-annotations for setup, teardown, etc)
        │   │   └── TestRunner.java         // Class for configurate and running Cucumber tests
        │   └── stepDefinitions
        │       └── HomePageSteps.java      // Step definitions for Cucumber scenarios related to homepage the features (wich are our test cases)
        └── resources
            ├── resources
            │   └── archive.feature        // Gherkin feature file defining scenarios for archiving
            ├── allure.properties          // Configuration file for Allure report settings
            └── project.properties 
```
> For this section we shrortly will see the relation between the clases we have in our project.
> - TestRunner is not referenced or used on any other class. Cucumber will automatically see on the project about the stepsDefinitions and hooksteps we declared with annotations on the TestRunner class
> - HookStep refers to WebDriver class. On WebDriver class we setup and close the driver and used we use @before & @after (test, suite, class, method, etc) on the HookSteps runner with Cucumber annotation for be understood by Cucumber.
> - WebDriver is used on HookSteps, as we see on the previous line.
> - Then Cucumber will see through the project and reach the stepDefinitions
> - The stepsDefinition (in our case: HomePageSteps) reference the main test (in our case: HomePage) and pass to the HomePage object the HomePageObjects to know what objects are used on the page.
> - On the HomePage are declared methods used on the HomePageSteps
> - HomePage is it used on HomePageSteps and refers to the Objects (HomePageObjects) that were passed on the HomePageSteps. Also the HomePage uses the WebActions where are the method relationaed with the base actions that can be performed on the driver
> - On HomePageObject finally we use the driver of WebDriver to use the already init driver.

# 2. pom.xml & Mandatory dependencies

This may be differ from your. Depends on the name of the package of your maven project. Does not have to be the same.
```
<groupId>org.framework.selenium</groupId>
<artifactId>java-core-web-automation-bdd</artifactId>
<version>1.0-SNAPSHOT</version>
```

This also may be different. Depends on your Java version (must be set on <maven.compiler.source> & <maven.compiler.target>). 
Then the allure.version & aspecj.version  used may be the same.
Finally cucumber.version & selenium.version may be different but remeber that has to be Cucumber 7 & Selenium 4.
```
<properties>
    <maven.compiler.source>13</maven.compiler.source>
    <maven.compiler.target>13</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <allure.version>2.24.0</allure.version>
    <aspectj.version>1.9.22</aspectj.version>
    <cucumber.version>7.16.1</cucumber.version>
    <selenium.version>4.19.1</selenium.version>
</properties>
```

This dependecyManagent is mandatory for being able to use Allure. If you download the starting-allure-generator you can see this dependencyManagment is set and obligatory for not having maven dependecy problems.
```
<dependencyManagement>
        <dependencies>
            <!-- Allure BOM -->
            <dependency>
                <groupId>io.qameta.allure</groupId>
                <artifactId>allure-bom</artifactId>
                <version>${allure.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!-- JUnit BOM -->
            <dependency>
                <groupId>org.junit</groupId>
                <artifactId>junit-bom</artifactId>
                <version>5.10.2</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!-- Cucumber BOM -->
            <dependency>
                <groupId>io.cucumber</groupId>
                <artifactId>cucumber-bom</artifactId>
                <version>${cucumber.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```
> - The <dependencyManagement> section in a Maven POM file allows you to define dependencies that can be shared across multiple modules within a project. It does not actually include the dependencies in your project; rather, it provides a way to centralize and manage version information for those dependencies.
> - In this section, three Bill of Materials (BOM) dependencies are defined: Allure BOM, JUnit BOM, and Cucumber BOM. BOM dependencies are special types of POM files that solely define version numbers for other dependencies. They are used to manage and align the versions of dependencies in multi-module projects.
> - Each BOM dependency includes the groupId, artifactId, and version. The ${allure.version} and ${cucumber.version} are placeholders that are replaced by the actual versions specified in the <properties> section of the POM file.
> - The <type> element specifies the packaging type of the dependency, which is set to pom indicating that these are POM files.
> - The <scope> element defines the scope of the dependency. In this case, it is set to import, which means that the dependencies defined in these BOM files will be imported into the project's dependency management.

> [!CAUTION]
> If you do not have the correct dependecies (or for example not having the <depedencyManagment>, or having the junit5 dependencies instead of the Cucumber-JVM ones, etc) & you may seen problems relationated with compabilty between
> dependecies. You will see your maven dependecy section all marked as red. You might re-do the pom.xml following the steps we share on 'Main classes & Functionality'.

Finally the only section to comment it is the build section.
```
<build>
        <plugins>
            <!-- Maven Surefire Plugin -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
                <configuration>
                    <testFailureIgnore>true</testFailureIgnore>
                    <argLine> -Dfile.encoding=${project.build.sourceEncoding} -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar" </argLine>
                    <systemPropertyVariables>
                        <allure.results.directory>${project.build.directory}/allure-results</allure.results.directory>
                    </systemPropertyVariables>
                </configuration>
                <dependencies>
                    <!-- AspectJ Weaver -->
                    <dependency>
                        <groupId>org.aspectj</groupId>
                        <artifactId>aspectjweaver</artifactId>
                        <version>${aspectj.version}</version>
                    </dependency>
                </dependencies>
            </plugin>
            <!-- Allure Maven Plugin -->
            <plugin>
                <groupId>io.qameta.allure</groupId>
                <artifactId>allure-maven</artifactId>
                <version>2.12.0</version>
                <configuration>
                    <reportVersion>${allure.version}</reportVersion>
                    <resultsDirectory>${project.build.directory}/allure-results</resultsDirectory>
                </configuration>
            </plugin>
            <!-- Maven Compiler Plugin -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.13.0</version>
                <configuration>
                    <encoding>${project.build.sourceEncoding}</encoding>
                    <release>13</release>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
> - The <build> section in a Maven POM file configures various aspects of the build process, including which plugins should be executed and how they should be configured.
> - Within the <plugins> element, different Maven plugins are defined, each with its own <plugin> configuration.

1. Maven Surefire Plugin:
This plugin is responsible for running unit tests in Maven projects.
Version 3.2.5 is specified for the plugin.
- The <configuration> element allows customizing the plugin's behavior. For example:
- <testFailureIgnore> is set to true, which means that the build will continue even if there are test failures.
- <argLine> defines system properties to be passed to the JVM when running tests. In this case, it sets the file encoding and specifies a Java agent for AspectJ weaving.
- <systemPropertyVariables> allows defining additional system properties for the tests.
- The <dependencies> element includes additional dependencies required by the Surefire plugin. Here, it includes AspectJ Weaver.
2. Allure Maven Plugin:
This plugin integrates Allure reporting into the Maven build process, allowing you to generate Allure reports for your tests.
Version 2.12.0 of the plugin is specified.
- The <configuration> element specifies the configuration options for the plugin. For example:
- <reportVersion> specifies the version of Allure reports to be generated.
- <resultsDirectory> specifies the directory where Allure results should be stored.
3. Maven Compiler Plugin:
This plugin is responsible for compiling the Java source code of the project.
Version 3.13.0 is specified.
- The <configuration> element allows configuring compiler options. Here, it sets the encoding of the source files and specifies the Java release version to use during compilation.

> [!NOTE]
> You should not change anything about the plugins configurated on the vuild section.


-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
RESUME OF SELENIUM & SELENIUM ACTUAL TOOLS
Selenium is a widely used open-source set of tools for automating testing in web applications. It allows developers and testers to programmatically automate interaction with web browsers, thereby facilitating the conduct of functional 
tests in various scenarios and conditions.

Here is a detailed description of the main components of Selenium:

Selenium WebDriver: Selenium WebDriver is the primary automation tool within the Selenium toolkit. It provides an API for programmatically interacting with web browsers. Developers can write scripts in various programming languages 
(such as Java, Python, C#, etc.) to automate user actions in the browser, such as clicking buttons, filling out forms, navigating web pages, and verifying elements on the page. WebDriver is powerful and flexible, allowing for a wide 
range of interactions with the user interface elements of a web application.
Selenium IDE: Selenium IDE is an open-source test automation development tool that enables recording and playback of interactions with the browser. It is a browser extension that facilitates the rapid creation of automated tests 
through its graphical user interface. Users can record their actions in a browser and then replay, edit, and debug them as needed. Selenium IDE is useful for quick and simple tests but may have limitations compared to WebDriver in 
terms of complexity and flexibility.
Selenium Grid: Selenium Grid is a tool that allows running Selenium tests in parallel on multiple machines and browsers simultaneously. It enables distributing tests among several nodes (virtual or physical machines) to speed up the 
testing process and increase platform coverage. Selenium Grid consists of a "hub" node that coordinates test requests and multiple "node" nodes that execute tests in different browser and platform environments. This is especially useful 
for efficiently conducting compatibility tests on different browsers and operating systems.

Regarding how the topic of executions on different platforms, environments, and serial and parallel executions comes into play, here are some considerations:
Different Platforms and Environments: Selenium WebDriver allows the execution of tests on a wide variety of platforms and environments, including different operating systems (Windows, macOS, Linux) and web browsers 
(Chrome, Firefox, Safari, etc.). This is achieved by installing and configuring specific drivers for each browser and operating system. For example, running tests on Chrome requires a Chrome driver (such as ChromeDriver) installed on 
the system.
Serial and Parallel Executions: Selenium WebDriver can execute tests both serially and in parallel, depending on the test requirements and environment configuration. Serial execution involves running a sequence of tests one after another,
while parallel execution involves running multiple tests simultaneously in different browsers or platforms. Selenium Grid facilitates parallel execution by distributing tests among multiple nodes. This can significantly improve the speed 
of test execution and allow faster coverage of test cases in multiple platform and browser configurations.
In summary, Selenium is a powerful suite of tools for web application test automation, with core components such as Selenium WebDriver, Selenium IDE, and Selenium Grid. It allows users to automate interactions with web browsers, execute 
tests on different platforms and environments, and perform both serial and parallel executions to improve testing efficiency and coverage

Lastly, Previously, there was also Selenium Remote Control (RC), an outdated Selenium tool that has been replaced by WebDriver. Selenium RC allowed users to write tests in various programming languages such as Java, C#, Python, etc., 
and run these tests across multiple browsers. However, Selenium RC had limitations and was discontinued in favor of WebDriver, which provides a more robust API and better architecture for test automation. Therefore, in the current 
context of Selenium, Selenium RC would not be used, and using WebDriver for test automation would be recommended instead.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
